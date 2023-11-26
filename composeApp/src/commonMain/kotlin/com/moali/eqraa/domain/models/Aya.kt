package com.moali.eqraa.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Aya(
    var sura_id:Int,
    var aya_id:Int,
    var aya_id_ar:String,
    var sura_name:String,
    var sura_name_ar:String,
    var standard_full:String,
    var juz_id:Int
)



