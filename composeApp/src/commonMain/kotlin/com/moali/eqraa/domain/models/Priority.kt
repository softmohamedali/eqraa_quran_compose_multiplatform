package com.moali.eqraa.domain.models

import androidx.compose.ui.graphics.Color
import com.moali.eqraa.ui.theme.high
import com.moali.eqraa.ui.theme.low
import com.moali.eqraa.ui.theme.med
import com.moali.eqraa.ui.theme.none
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.StringResource

enum class Priority(
    var color:Color,
    var text:StringResource
) {
    LOW(low, SharedRes.strings.low),
    MEDIUM(med,SharedRes.strings.medium),
    HIGH(high,SharedRes.strings.high),
    NONE(Color.White,SharedRes.strings.none),
}

data class PriorityEntity(
    val id:Int=1,
    val type:String
)

object PriorityType{
    const val LOW="LOW"
    const val HIGH="HIGH"
    const val MEDIUM="MEDIUM"

}

fun getPropertyColor(type:String):Color{
    return when{
        type==PriorityType.LOW -> low
        type==PriorityType.HIGH -> high
        type==PriorityType.MEDIUM -> med
        else -> none
    }
}

fun getPropertyFromString(type:String):Priority{
    return when{
        type==PriorityType.LOW -> Priority.LOW
        type==PriorityType.HIGH -> Priority.HIGH
        type==PriorityType.MEDIUM -> Priority.MEDIUM
        else -> Priority.NONE
    }
}