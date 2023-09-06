package com.moali.eqraa.presentation.screens.quran

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.di.DIManualAppModule
import com.moali.eqraa.domain.usecases.GetQuranUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class QuranViewModel (
    private val getQuranUseCase: GetQuranUseCase = DIManualAppModule.getQuranUseCase,
    private val dispatchers: Dispatchers =DIManualAppModule.disPatchers
):ViewModel(){



    var state by mutableStateOf(QuranState())

    init {
        getQuran()
    }

    fun onEvent(events: QuranEvents){

    }

    private fun getQuran(){
        viewModelScope.launch(dispatchers.main) {
            getQuranUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(it.data!!)
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