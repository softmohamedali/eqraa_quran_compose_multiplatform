package com.moali.eqraa.presentation.screens.juza

import androidx.compose.foundation.text.InlineTextContent
import com.moali.eqraa.domain.models.Soura

data class JuzaMapData(
    val souraMap:Map<String, InlineTextContent>,
    val soura: Soura,
)
