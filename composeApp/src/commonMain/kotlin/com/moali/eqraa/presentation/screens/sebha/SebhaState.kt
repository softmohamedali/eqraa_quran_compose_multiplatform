package com.moali.eqraa.presentation.screens.sebha

import com.moali.eqraa.domain.models.Soura

data class SebhaState(
    val error:String?=null,
    val counter:Int=0,
    val currentTasbehaId:Int=0,
    val showingRequestDialog:Boolean=false
) {
}