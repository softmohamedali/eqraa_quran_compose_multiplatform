package com.moali.eqraa.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Aya(var sura_id:Int, var aya_id:Int, var sura_name:String, var standard_full:String ) {

}

