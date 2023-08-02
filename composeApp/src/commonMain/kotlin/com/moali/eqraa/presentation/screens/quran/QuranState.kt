package com.moali.eqraa.presentation.screens.quran

import com.moali.eqraa.domain.models.Soura

data class QuranState(
    val sour:List<Soura> = emptyList()
)