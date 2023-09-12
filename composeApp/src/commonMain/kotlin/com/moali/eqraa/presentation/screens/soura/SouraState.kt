package com.moali.eqraa.presentation.screens.soura

import com.moali.eqraa.domain.models.Soura

data class SouraState(
    val error:String?=null,
    val isLoading:Boolean=false,
    val souraId:Int=0,
    val soura:Soura= Soura(-1,"", arrayListOf()),
    val speed:String="",
    val isPlay:Boolean=false,
    val isShowBottomAudioSheet:Boolean=false,
    val currentAudioProgress:Float=0.0f,
    val totalProgress:Float=0.0f,
    val currentTime:String="00:00",
    val totalTime:String="00:00",
) {
}