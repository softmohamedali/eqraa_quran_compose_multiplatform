package com.moali.eqraa.presentation.screens.sebha

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SebhaViewModel : ViewModel(), KoinComponent {

    private val dispatchers: Dispatchers by inject()
    private val pref: DataStoreOper by inject()
    private val servicesUtils: ServicesUtils by inject()


    var state by mutableStateOf(SebhaState())

    init {
        viewModelScope.launch(dispatchers.main) {
            pref.getSebhaPrefAsFlow().collect {
                state = state.copy(
                    counter = pref.getSebhaCounter() ?: 0
                )
            }
        }
    }


    fun onEvent(events: SebhaEvents) {
        when (events) {
            is SebhaEvents.OnIncreaseClick -> {
                state = state.copy(counter = state.counter + 1)
                saveSebhaState()
            }

            is SebhaEvents.OnRestartClick -> {
                state = state.copy(counter = 0)
                saveSebhaState()
            }

            is SebhaEvents.OnBackStepClick -> {
                if (state.counter > 0) {
                    state = state.copy(counter = state.counter - 1)
                }
                saveSebhaState()
            }

            is SebhaEvents.OnFloatingSebhaClicked -> {
                servicesUtils.startServiceIntentToCreateSebhaFloating()
            }
        }
    }

    private fun saveSebhaState() {
        viewModelScope.launch(dispatchers.io) {
            pref.saveSebhaCounter(state.counter)
        }
    }


    override fun onCleared() {
//        viewModelScope.launch(dispatchers.io) {
//
//            pref.saveSebhaCounter(state.counter)
//        }.invokeOnCompletion {
//            viewModelScope.cancel()
//        }
        super.onCleared()

    }


}