package com.moali.eqraa.presentation.screens.sebha

sealed class SebhaEvents {
    object OnIncreaseClick:SebhaEvents()
    object OnRestartClick:SebhaEvents()
    object OnBackStepClick:SebhaEvents()

    object OnFloatingSebhaClick:SebhaEvents()
    object OnGoSettingClick:SebhaEvents()
    object OnCloseAlertClick:SebhaEvents()
    class OnTasbehaItemClick(val id:Int):SebhaEvents()


}