package com.moali.eqraa.core.shared.services

import android.content.Context
import android.content.Intent
import com.moali.eqraa.core.services.audio_services.ActionAudioService
import com.moali.eqraa.core.services.audio_services.AudioServices
import com.moali.eqraa.core.services.floating_services.SebhaFloatingServices
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND_COUNTDOWN_CREATE
import com.moali.eqraa.core.utils.Constants.SHEK_NAME_EXTRA
import com.moali.eqraa.core.utils.Constants.SOURA_NAME_EXTRA


actual class ServicesUtils (
    private val context: Context
){
    actual fun startServiceIntentToCreatePlayerNotification(
        souraName:String,
        shehkName:String
    ){
        Intent(context, AudioServices::class.java).also {
            it.putExtra(SOURA_NAME_EXTRA,souraName)
            it.putExtra(SHEK_NAME_EXTRA,shehkName)
            it.action= ActionAudioService.START.toString()
            context.startService(it)
        }
    }

    actual fun startServiceIntentToCreateSebhaFloating(){
        Intent(context, SebhaFloatingServices::class.java).also {
            it.putExtra(INTENT_COMMAND, INTENT_COMMAND_COUNTDOWN_CREATE)
            context.startService(it)
        }
    }



}