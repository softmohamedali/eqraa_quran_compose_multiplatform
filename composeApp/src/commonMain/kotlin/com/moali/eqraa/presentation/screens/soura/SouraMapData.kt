package com.moali.eqraa.presentation.screens.soura

import androidx.compose.foundation.text.InlineTextContent
import com.moali.eqraa.domain.models.Soura

data class SouraMapData(
    val souraMap:Map<String, InlineTextContent> = mapOf(),
    val soura: Soura?=null,
)