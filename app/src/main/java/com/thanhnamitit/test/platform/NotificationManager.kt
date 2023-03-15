package com.thanhnamitit.test.platform

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.thanhnamitit.test.R
import com.thanhnamitit.test.presentation.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val CHANNEL_ID = "match_notifier"
const val CHANNEL_NAME = "Match Notifier"


class NotificationManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun notify(title: String, message: String, id: Int) {
        NotificationManagerCompat.from(context).notify(id, buildNotification(title, message))
    }

    private fun buildNotification(
        title: String,
        message: String,
    ): Notification {
        val fullscreenIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            6626,
            fullscreenIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        createCallNotificationChannel()
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setDefaults(NotificationCompat.DEFAULT_SOUND)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentTitle(title)
            .setContentText(message)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setFullScreenIntent(pendingIntent, true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()
    }


    private fun createCallNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            NotificationManagerCompat.from(context).createNotificationChannel(channel)
        }
    }
}