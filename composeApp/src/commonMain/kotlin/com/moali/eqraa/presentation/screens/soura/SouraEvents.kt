package com.moali.eqraa.presentation.screens.soura

import com.moali.eqraa.domain.models.Soura

sealed class SouraEvents {
    object OnAudioMiniClick:SouraEvents()
    object OnPauseClick:SouraEvents()
    object OnResumeClick:SouraEvents()
    object OnStopClick:SouraEvents()
    object OnSpeedClick:SouraEvents()
    object OnNextClick:SouraEvents()
    object OnPreviousClick:SouraEvents()
    object OnRepeatClick:SouraEvents()
    class OnSeekChange(val value:Float):SouraEvents()

    object OnSeekFinishedChange:SouraEvents()
    object OnRandomClick:SouraEvents()
    object OnTeenForwardClick:SouraEvents()
    object OnTeenBackWardClick:SouraEvents()
    object OnCloseBottomSheetClick:SouraEvents()

    class OnInit(val souraId:Int):SouraEvents()


}