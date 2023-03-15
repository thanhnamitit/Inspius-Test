package com.thanhnamitit.test.platform.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.thanhnamitit.test.Constants
import com.thanhnamitit.test.R
import com.thanhnamitit.test.domain.usecase.GetNotifyMatchUseCase
import com.thanhnamitit.test.platform.NotificationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var getNotifyMatchUseCase: GetNotifyMatchUseCase

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent?) {
        when (intent?.action) {
            Constants.ACTION_NOTIFY -> {
                intent.getLongExtra(Constants.EXTRA_ID, -1L).takeIf {
                    it != -1L
                }?.let { uid ->
                    goAsync {
                        getNotifyMatchUseCase(uid)?.let { match ->
                            notificationManager.notify(
                                match.description,
                                context.resources.getString(R.string.the_match_will_start_soon),
                                uid.toInt()
                            )
                        }
                    }
                }
            }
        }
    }
}

fun BroadcastReceiver.goAsync(
    context: CoroutineContext = EmptyCoroutineContext,
    block: suspend CoroutineScope.() -> Unit,
) {
    val pendingResult = goAsync()
    CoroutineScope(SupervisorJob()).launch(context) {
        try {
            block()
        } finally {
            pendingResult.finish()
        }
    }
}

