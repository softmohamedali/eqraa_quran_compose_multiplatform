package com.moali.eqraa.presentation.screens.soura

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.moali.eqraa.core.shared.Dispatchers
import com.moali.eqraa.domain.abstractions.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.MediaPlayerOperation
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SouraViewModel : ViewModel(), KoinComponent {

    private val mediaPlayerController: MediaPlayerOperation by inject()
    private val dispatchers: Dispatchers by inject()

    var state by mutableStateOf(SouraState())

    fun onEvent(events: SouraEvents) {
        when (events) {
            is SouraEvents.OnInit -> {
                state = state.copy(soura = events.soura)
                prepere()
            }

            is SouraEvents.OnAudioMiniClick -> {
                onAudioMiniItemClick()
            }

            is SouraEvents.OnPauseClick -> {
                onResumePause()
            }

            is SouraEvents.OnSpeedClick -> {

            }

            is SouraEvents.OnNextClick -> {
                onNext()
            }

            is SouraEvents.OnPreviousClick -> {

            }

            is SouraEvents.OnRepeatClick -> {

            }

            is SouraEvents.OnSeekChange -> {

            }

            is SouraEvents.OnRandomClick -> {

            }

            is SouraEvents.OnTeenForwardClick -> {

            }

            is SouraEvents.OnTeenBackWardClick -> {

            }

            is SouraEvents.OnResumeClick -> {
                onResumePause()
            }

            is SouraEvents.OnStopClick -> {

            }
        }
    }

    private fun onAudioMiniItemClick() {
        state=state.copy(isShowBottomAudioSheet = true)
    }

    private fun onNext() {

    }

    private fun onResumePause() {
        if (!mediaPlayerController.isPlaying()) {
            state = state.copy(
                isPlay = true
            )
            mediaPlayerController.start()
        } else {
            state = state.copy(
                isPlay = false
            )
            mediaPlayerController.pause()

        }
    }


    private fun prepere() {
        mediaPlayerController
            .prepare("https://server8.mp3quran.net/afs/${countNumberDigets(state.soura.id)}.mp3",
                listener = object : MediaPlayerListener {
                    override fun onReady() {

                    }

                    override fun onVideoCompleted() {
                    }

                    override fun onError() {

                    }
                }
            )
    }

    private fun onStop() {
        mediaPlayerController.stop()
    }

    private fun countNumberDigets(num: Int): String {
        if (num in 1..9) {
            return "00${num}"
        }
        if (num in 10..99) {
            return "0${num}"
        } else {
            return num.toString()
        }
    }


}