package com.moali.eqraa.core.shared.utils.permission

interface PermissionHandler {
    fun requestLocationPermission()
    fun isLocationPermissionGranted(): Boolean
}