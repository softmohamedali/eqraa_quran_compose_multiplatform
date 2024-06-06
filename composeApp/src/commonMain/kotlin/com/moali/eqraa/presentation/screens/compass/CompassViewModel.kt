package com.moali.eqraa.presentation.screens.compass

import com.moali.eqraa.core.shared.utils.CompassSensorManager
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class CompassViewModel : ViewModel(), KoinComponent {

    private val compassSensorManager: CompassSensorManager by inject()

    val azimuth = compassSensorManager.azimuth()
    val qiblaDirection = compassSensorManager.qiblaDirection()

    init {
        compassSensorManager.start()
    }


    override fun onCleared() {
        compassSensorManager.stop()
        super.onCleared()
    }
}