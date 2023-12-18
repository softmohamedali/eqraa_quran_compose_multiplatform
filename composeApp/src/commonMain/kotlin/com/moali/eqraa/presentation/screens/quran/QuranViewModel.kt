package com.moali.eqraa.presentation.screens.quran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.di.DIManualAppModule
import com.moali.eqraa.domain.usecases.GetQuranAsJuzaUseCase
import com.moali.eqraa.domain.usecases.GetQuranUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class QuranViewModel :ViewModel(),KoinComponent{

    private val getQuranUseCase: GetQuranUseCase by inject()
    private val getQuranAsJuzaUseCase: GetQuranAsJuzaUseCase by inject()
    private val dispatchers: Dispatchers by inject()
    private val servicesUtils: ServicesUtils by inject()

    var state by mutableStateOf(QuranState())

    init {
        state=state.copy(lang = servicesUtils.getCurrentLanguage())
        getQuran()
        getQuranAsJuza()
    }

    fun onEvent(events: QuranEvents){

    }

    private fun getQuran(){
        viewModelScope.launch(dispatchers.main) {
            getQuranUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(
                            sour=it.data!!
                        )
                    }
                    it is ResultState.IsLoading ->{

                    }
                    it is ResultState.IsError ->{

                    }
                }

            }
        }
    }
    private fun getQuranAsJuza(){
        viewModelScope.launch(dispatchers.main) {
            getQuranAsJuzaUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(
                            ajza = it.data!!
                        )
                    }
                    it is ResultState.IsLoading ->{

                    }
                    it is ResultState.IsError ->{

                    }
                }

            }
        }
    }
}