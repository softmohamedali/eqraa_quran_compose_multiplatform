package com.moali.eqraa.presentation.screens.juza

import com.moali.eqraa.domain.models.Juza

data class JuzaState(
    val error:String?=null,
    val isLoading:Boolean=true,
    val juzaId:Int=0,
    val juza:Juza= Juza(-1, arrayListOf()),
    val juzaMap:List<JuzaMapData> = listOf(),
    val scrollPotion:Int=0,
    val lang:String="en",
) {
}