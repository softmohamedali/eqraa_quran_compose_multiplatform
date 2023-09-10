package com.moali.eqraa.core.services.floating_services.floating_component

import android.view.Gravity
import android.view.WindowManager
import com.moali.eqraa.core.services.floating_services.SebhaFloatingServices

class OverlayViewHolder(
  val params: WindowManager.LayoutParams,
  service: SebhaFloatingServices
) {
  val view = overlayViewFactory(service)

  init {
    params.gravity = Gravity.TOP or Gravity.LEFT
  }
}