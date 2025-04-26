package com.moali.eqraa.core.shared.utils

import kotlinx.coroutines.flow.Flow

actual class CompassSensorManager {
    var azimuth: Float
        get() = TODO("Not yet implemented")
        set(value) {}

    actual fun start() {
    }

    actual fun stop() {
    }

    actual fun azimuth(): Flow<Float> {
        TODO("Not yet implemented")
    }

    actual fun qiblaDirection(): Flow<Double> {
        TODO("Not yet implemented")
    }

}