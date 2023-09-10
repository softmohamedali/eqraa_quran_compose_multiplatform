package com.moali.eqraa.core.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.moali.eqraa.core.services.audio_services.AudioServices
import com.moali.eqraa.core.utils.log

class NotificationActionBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        log("onReceive intent action${intent?.action}","services warf")
        if (intent?.action != null) {
            Intent(context, AudioServices::class.java).also {
                it.action=intent.action
                context?.startService(it)
            }
        }
    }


}