package com.moali.eqraa.presentation.screens.juza

import com.moali.eqraa.domain.models.Juza
import com.moali.eqraa.domain.models.Soura

data class JuzaState(
    val error:String?=null,
    val isLoading:Boolean=true,
    val juzaId:Int=0,
    val juza:Juza= Juza(-1, arrayListOf()),
    val scrollPotion:Int=0,
    val lang:String="en",
) {
}