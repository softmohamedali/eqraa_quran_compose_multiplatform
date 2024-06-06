package com.moali.eqraa.core.shared.utils.permission

expect class AndroidPermissionCheck {

    fun isLocationPermissionGranted(): Boolean
    fun openAppSettings()
}