package com.moali.eqraa

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.os.Build
import com.moali.eqraa.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin { androidContext(this@App) }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationAudioChannel = NotificationChannel(
                "audio_notifi",
                "audio_channel",
                    IMPORTANCE_HIGH
                )

            val notificationManger=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManger.createNotificationChannel(notificationAudioChannel)
        }

    }
}
