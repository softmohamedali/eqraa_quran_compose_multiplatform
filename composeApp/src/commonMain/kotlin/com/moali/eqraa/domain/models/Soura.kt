package com.moali.eqraa.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class Soura(
    var id:Int,
    var name_ar:String,
    var name:String,
    var soura:MutableList<Aya>
) {

}

fun supSouraAyat(soura: MutableList<Aya>):String
{
    var completesoura:String=""
    for (i in 0 until soura.size)
    {
        //
        completesoura+=" ${soura[i].standard_full} ⊰⦅${i+1}⦆⊱ "
    }
    return completesoura
}