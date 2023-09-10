package com.moali.eqraa.core.services.floating_services.floating_component


import androidx.compose.runtime.mutableStateOf

class SebhaFloatingState {
  val overlayState = OverlayState()
  val counter= mutableStateOf(0)

}

sealed class SebhaEvents {}

object SebhaEventsRunning : SebhaEvents()

