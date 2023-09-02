package com.moali.eqraa.core.shared

import android.content.Context
import android.content.Intent
import com.moali.eqraa.core.services.ActionAudioService
import com.moali.eqraa.core.services.AudioServices

actual class ServicesUtils (
    private val context: Context
){
    actual fun startServiceIntent(){
        Intent(context, AudioServices::class.java).also {
            it.action= ActionAudioService.START.toString()
            context.startService(it)
        }
    }

    actual fun createPlayerNotification(){

    }

}