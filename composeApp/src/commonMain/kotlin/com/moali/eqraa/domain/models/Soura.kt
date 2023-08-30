package com.moali.eqraa.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class Soura(var id:Int, var name:String, var soura:ArrayList<Aya>) {
    fun sup():String
    {
        var completesoura:String=""
        for (i in 0 until soura.size)
        {
            //
            completesoura+=" ${soura[i].standard_full} ⊰⦅${i+1}⦆⊱ "
        }
        return completesoura
    }
}