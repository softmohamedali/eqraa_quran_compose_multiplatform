package com.moali.eqraa.domain.models

import androidx.compose.ui.graphics.Color
import com.moali.eqraa.ui.theme.high
import com.moali.eqraa.ui.theme.low
import com.moali.eqraa.ui.theme.med
import com.moali.eqraa.ui.theme.none

enum class Priority(var color:Color) {
    LOW(low),
    MEDIUME(med),
    HIGH(high),
    NONE(none),
}