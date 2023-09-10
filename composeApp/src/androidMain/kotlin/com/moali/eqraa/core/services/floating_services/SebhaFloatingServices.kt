package com.moali.eqraa.core.services.floating_services

import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.moali.eqraa.core.services.floating_services.floating_component.OverlayController
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND_COUNTDOWN_CREATE
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND_EXIT
import com.torrydo.screenez.ScreenEz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SebhaFloatingServices : Service() {

    private val job = SupervisorJob()
    val scope = CoroutineScope(Dispatchers.Default + job)

    private lateinit var overlayController: OverlayController


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        ScreenEz.with(this.applicationContext)
        overlayController = OverlayController(this,)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let {
            when (intent.getStringExtra(INTENT_COMMAND)) {
                INTENT_COMMAND_COUNTDOWN_CREATE -> {
                    overlayController.startCountDown()
                }

                INTENT_COMMAND_EXIT -> {
                    if (overlayController.isCountDownVisible()) {
                        overlayController.exitCountdown()
                    }
                    return START_NOT_STICKY
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ScreenEz.refresh()
        scope.launch {
            overlayController.configurationChanged.emit(Unit)
        }
    }


}



