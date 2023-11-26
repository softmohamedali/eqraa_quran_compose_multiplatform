package com.moali.eqraa.domain.usecases

import com.moali.eqraa.domain.abstractions.repo.EqraaRepo
import kotlinx.coroutines.flow.flow

class GetQuranAsJuzaUseCase(
    private val repo: EqraaRepo
) {
    suspend operator fun invoke() = flow {
        emit(repo.getQuranAsJuza())
    }
}