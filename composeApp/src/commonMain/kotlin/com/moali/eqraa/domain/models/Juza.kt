package com.moali.eqraa.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class Juza(
    var id:Int,
    var sour:MutableList<Soura>
) {

}

