package com.moali.eqraa.presentation.screens.sebha

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.Dispatchers
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SebhaViewModel : ViewModel(), KoinComponent {

    private val dispatchers: Dispatchers by inject()
//    private val sharedPref: Settings by inject()
    private val  pref: DataStoreOper by inject()



    var state by mutableStateOf(SebhaState())

    init {
        viewModelScope.launch (dispatchers.main){
            log("counter --->${pref.getSebhaCounter()?:0}","viewmodel sebha")
            state=state.copy(
                counter = pref.getSebhaCounter()?:0
            )
        }
    }



    fun onEvent(events: SebhaEvents) {
        when (events) {


            is SebhaEvents.OnIncreaseClick -> {
                state=state.copy(counter = state.counter+1 )
            }

            is SebhaEvents.OnRestartClick -> {
                state=state.copy(counter = 0 )
            }
            is SebhaEvents.OnBackStepClick -> {
                state=state.copy(counter = state.counter-1 )
            }
            is SebhaEvents.OnBackClick -> {
                viewModelScope.launch(dispatchers.io) {
                    log("oncleared" ,"viewmodel sdeha")
                    pref.saveSebhaCounter(state.counter)
                }
            }
        }
    }


    override fun onCleared() {
        viewModelScope.launch(dispatchers.io) {
            log("oncleared" ,"viewmodel sdeha")
            pref.saveSebhaCounter(state.counter)
        }.invokeOnCompletion {
            viewModelScope.cancel()
        }

    }


}