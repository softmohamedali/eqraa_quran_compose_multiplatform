package com.moali.eqraa.core.utils

sealed class PlatformType(val name:String){
    object ANDROID:PlatformType("ANDROID")
    object IOS:PlatformType("IOS")
    object DESKTOP:PlatformType("DESKTOP")
    object WEB:PlatformType("WEB")
}