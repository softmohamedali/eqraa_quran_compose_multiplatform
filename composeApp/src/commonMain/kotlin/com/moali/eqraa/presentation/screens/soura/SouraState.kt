package com.moali.eqraa.presentation.screens.soura

import com.moali.eqraa.domain.models.Soura

data class SouraState(
    val error:String?=null,
    val soura:Soura= Soura(-1,"", arrayListOf()),
    val speed:String="",
    val startPeriod:String="",
    val endPeriod:String="",
    val isPlay:Boolean=false,
    val isShowBottomAudioSheet:Boolean=false,
    val currentAudioTime:String="",
    val currentAudioProgress:Float=0.0f
) {
}