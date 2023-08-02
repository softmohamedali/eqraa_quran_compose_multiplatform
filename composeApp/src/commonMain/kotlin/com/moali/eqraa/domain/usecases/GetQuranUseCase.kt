package com.moali.eqraa.domain.usecases

import com.moali.eqraa.domain.abstractions.EqraaRepo
import kotlinx.coroutines.flow.flow

class GetQuranUseCase(
    private val repo: EqraaRepo
) {
    suspend operator fun invoke() = flow {
        emit(repo.getQuran())

    }
}