package com.moali.eqraa.di

import com.moali.eqraa.core.shared.Dispatchers
import com.moali.eqraa.core.shared.provideDisPatchers
import com.moali.eqraa.data.repo.EqraaRepoImp
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.abstractions.EqraaRepo
import com.moali.eqraa.domain.usecases.GetQuranUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val dataModule= module {
    single { QuranJsonRecourse() }
}

val domainModule= module {
    factory { GetQuranUseCase(get()) }
    single<EqraaRepo> { EqraaRepoImp(get(),get()) }
}

val utilsModule= module {
    factory<Dispatchers> { provideDisPatchers() }
}

val sharedModules= listOf(dataModule, domainModule, utilsModule)

//fun getSharedModule()= sharedModule

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    modules(sharedModules)
    appDeclaration()
}

fun initKoin() = initKoin { }
