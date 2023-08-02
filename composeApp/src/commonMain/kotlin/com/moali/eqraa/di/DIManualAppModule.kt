package com.moali.eqraa.di

import com.moali.eqraa.core.shared.provideDisPatchers
import com.moali.eqraa.data.repo.EqraaRepoImp
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.usecases.GetQuranUseCase

object DIManualAppModule {
    private val quranJsonRecourse=QuranJsonRecourse()
    val disPatchers=provideDisPatchers()
    val eqraaRepoImp=EqraaRepoImp(quranJsonRecourse, disPatchers)
    val getQuranUseCase=GetQuranUseCase(eqraaRepoImp)

}





