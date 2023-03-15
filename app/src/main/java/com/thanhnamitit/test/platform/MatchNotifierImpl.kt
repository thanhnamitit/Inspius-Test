package com.thanhnamitit.test.platform

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.thanhnamitit.test.platform.receiver.AlarmReceiver
import com.thanhnamitit.test.Constants
import com.thanhnamitit.test.data.MatchNotifier
import com.thanhnamitit.test.domain.model.Match
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

class MatchNotifierImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : MatchNotifier {

    private val Context.alarmManager: AlarmManager
        get() {
            return getSystemService(Context.ALARM_SERVICE) as AlarmManager
        }

    private fun buildPendingIntent(context: Context, match: Match): PendingIntent {
        return Intent(context, AlarmReceiver::class.java).apply {
            action = Constants.ACTION_NOTIFY
            putExtra(Constants.EXTRA_ID, match.id)
        }.let { intent ->
            PendingIntent.getBroadcast(
                context,
                match.id.toInt(),
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        }

    }

    override fun requestToNotifyMatch(math: Match) {
        val alarmIntent = buildPendingIntent(context, math)
        context.alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            math.dateInMillis - PRE_NOTIFY_TIME_IN_MILLIS,
//            Calendar.getInstance().timeInMillis + 5000,
            alarmIntent
        )
    }

    override fun cancelNotifyMatch(math: Match) {
        val alarmIntent = buildPendingIntent(context, math)
        context.alarmManager.cancel(alarmIntent)
    }

    companion object {
        const val PRE_NOTIFY_TIME_IN_MILLIS = Constants.PRE_NOTIFY_TIME_IN_MINUTE * 60 * 1000
    }
}