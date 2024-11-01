package com.moali.eqraa.core.shared.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import com.moali.eqraa.core.services.audio_services.ActionAudioService
import com.moali.eqraa.core.services.audio_services.AudioServices
import com.moali.eqraa.core.services.floating_services.SebhaFloatingServices
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND
import com.moali.eqraa.core.services.floating_services.floating_component.INTENT_COMMAND_COUNTDOWN_CREATE
import com.moali.eqraa.core.utils.Constants.SHEK_NAME_EXTRA
import com.moali.eqraa.core.utils.Constants.SOURA_NAME_EXTRA
import com.moali.eqraa.domain.abstractions.local.DataStoreOper


actual class ServicesUtils (
    private val context: Context,
    private val pref: DataStoreOper
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!canDrawOverlays()) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.packageName)
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
                return
            }
            else{
                Intent(context, SebhaFloatingServices::class.java).also {
                    it.putExtra(INTENT_COMMAND, INTENT_COMMAND_COUNTDOWN_CREATE)
                    context.startService(it)
                }
            }
        }
    }

    actual fun canDrawOverlays():Boolean{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context)
        }
        return false
    }

    actual suspend fun startMainComponentAction(mainComponentActionId:Int) {
        pref.sendAction(mainComponentActionId)
    }


    actual fun getCurrentLanguage(): String {
        val resources = context.resources
        val configuration = resources.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return configuration.locales.get(0).language
        } else {
            @Suppress("DEPRECATION")
            return configuration.locale.language
        }
    }


}

