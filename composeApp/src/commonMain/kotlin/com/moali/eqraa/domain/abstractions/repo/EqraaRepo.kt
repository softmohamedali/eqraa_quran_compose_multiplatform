package com.moali.eqraa.domain.abstractions.repo

import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.domain.models.Soura

interface EqraaRepo {
    suspend fun getQuran():ResultState<List<Soura>>
}