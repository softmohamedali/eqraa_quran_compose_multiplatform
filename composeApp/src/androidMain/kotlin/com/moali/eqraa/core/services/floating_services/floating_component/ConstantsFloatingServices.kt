package com.moali.eqraa.core.services.floating_services.floating_component

import android.content.res.Resources

const val TRASH_SIZE_DP = 100
const val TIMER_SIZE_DP = 200
val TIMER_SIZE_PX = (TIMER_SIZE_DP * Resources.getSystem().displayMetrics.density).toInt()

const val INTENT_COMMAND = "xyz.tberghuis.floatingtimer.COMMAND"
const val INTENT_COMMAND_COUNTDOWN_CREATE = "countdown_create"


const val INTENT_COMMAND_EXIT = "command_exit"
