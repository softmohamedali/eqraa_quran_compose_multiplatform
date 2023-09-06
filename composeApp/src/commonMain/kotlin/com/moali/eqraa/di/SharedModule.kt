package com.moali.eqraa.di

import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.shared.utils.provideDisPatchers
import com.moali.eqraa.data.remote.QuranAudioServiceImp
import com.moali.eqraa.data.repo.EqraaRepoImp
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.abstractions.remote.QuranAudioService
import com.moali.eqraa.domain.abstractions.repo.EqraaRepo
import com.moali.eqraa.domain.usecases.GetQuranUseCase
import com.moali.eqraa.presentation.screens.note_details.NoteDetailsViewModel
import com.moali.eqraa.presentation.screens.notes.NoteViewModel
import com.moali.eqraa.presentation.screens.soura.SouraViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val remoteModule= module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                })
            }
            install(Logging){
                level=LogLevel.ALL
            }

        }
    }

    single <QuranAudioService>{
        QuranAudioServiceImp(get())
    }
}

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

val viewModelModule= module {
    singleOf(::NoteViewModel)
    singleOf(::NoteDetailsViewModel)
    singleOf(::SouraViewModel)

}

val sharedModules= listOf(
    remoteModule,
    dataModule,
    domainModule,
    utilsModule,
    viewModelModule,
    platformModule()
)

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    modules(sharedModules)
    appDeclaration()
}

fun initKoin() = initKoin { }
