package com.moali.eqraa.presentation.screens.sebha

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.utils.log
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
        viewModelScope.launch {
            pref.getCurrentTasbeha().collect {
                log("tasbeha id ->${it}","SebhaViewModel")
                state = state.copy(
                    currentTasbehaId = it?: 0
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
            is SebhaEvents.OnFloatingSebhaClick -> {
                if (!servicesUtils.canDrawOverlays()){
                    state = state.copy(showingRequestDialog = true)
                }else{
                    servicesUtils.startServiceIntentToCreateSebhaFloating()
                }
            }
            is SebhaEvents.OnGoSettingClick -> {
                servicesUtils.startServiceIntentToCreateSebhaFloating()
            }
            is SebhaEvents.OnCloseAlertClick -> {
                state = state.copy(showingRequestDialog = false)
            }
            is SebhaEvents.OnTasbehaItemClick -> {
                saveCurrentTasbeha(events.id)
            }
        }
    }

    private fun saveSebhaState() {
        viewModelScope.launch(dispatchers.io) {
            pref.saveSebhaCounter(state.counter)
        }
    }

    private fun saveCurrentTasbeha(id:Int) {
        log("save tasbeha id ->${id}","SebhaViewModel")
        viewModelScope.launch(dispatchers.io) {
            pref.saveTasbeha(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }


}