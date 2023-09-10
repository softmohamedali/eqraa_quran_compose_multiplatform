package com.moali.eqraa.presentation.screens.soura

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.domain.abstractions.media.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.media.MediaPlayerOperation
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SouraViewModel : ViewModel(), KoinComponent {

    private val mediaPlayerController: MediaPlayerOperation by inject()
    private val dispatchers: Dispatchers by inject()
    private val servicesUtils: ServicesUtils by inject()

    var state by mutableStateOf(SouraState())

    init {
        viewModelScope.launch {
            mediaPlayerController.playPauseState.collect{
                state=state.copy(isPlay = it)
            }
        }

    }

    fun onEvent(events: SouraEvents) {
        when (events) {
            is SouraEvents.OnInit -> {
                state = state.copy(soura = events.soura)
                prepere()
            }

            is SouraEvents.OnAudioMiniClick -> {
                openBottomSheet()
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
                state=state.copy(
                    currentAudioProgress = events.value,
                )
            }

            is SouraEvents.OnRandomClick -> {

            }

            is SouraEvents.OnTeenForwardClick -> {
                mediaPlayerController.skip10Sec()
            }

            is SouraEvents.OnTeenBackWardClick -> {
                mediaPlayerController.back10Sec()
            }

            is SouraEvents.OnResumeClick -> {
                onResumePause()
            }

            is SouraEvents.OnStopClick -> {

            }

            is SouraEvents.OnCloseBottomSheetClick -> {
                closeBottomSheet()
            }

            is SouraEvents.OnSeekFinishedChange -> {
                mediaPlayerController.navigateTo(state.currentAudioProgress.toInt())
            }

        }
    }

    private fun openBottomSheet() {
        state=state.copy(isShowBottomAudioSheet = true)
    }

    private fun closeBottomSheet() {
        state=state.copy(isShowBottomAudioSheet = false)
    }

    private fun onNext() {

    }

    private fun onResumePause() {
        if (!mediaPlayerController.isPlaying()) {
            mediaPlayerController.start()
            servicesUtils.startServiceIntentToCreatePlayerNotification()
        } else {
            mediaPlayerController.pause()

        }
    }


    private fun prepere() {
        mediaPlayerController
            .prepare("https://server8.mp3quran.net/afs/${countNumberDigets(state.soura.id)}.mp3",
                listener = object : MediaPlayerListener {
                    override fun onReady() {
//                        Logger.i { "duration ------>${ mediaPlayerController.getDuration()?:"00:00"}" }
                        state=state.copy(
                            totalTime = convertMillisecondsToTime(
                                mediaPlayerController.getDuration()
                            ),
                            totalProgress = mediaPlayerController.getDuration()?.toFloat()?:0f
                        )

                        viewModelScope.launch {
                            mediaPlayerController.current.collect{
                                state=state.copy(
                                    currentTime =  convertMillisecondsToTime(it),
                                    currentAudioProgress = it?.toFloat()?:0f
                                )
                            }

                        }
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

    fun convertMillisecondsToTime(milliseconds: Int?): String{
        if (milliseconds==null){
            return "00:00"
        }
        val seconds = (milliseconds / 1000)
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val remainingSeconds = seconds % 60

        return "${formatDigit(hours)}:${formatDigit(minutes)}:${formatDigit(remainingSeconds)}"
    }

    fun formatDigit(number: Int): String {
        return if (number in 0..9) {
            "0$number"
        } else {
            number.toString()
        }
    }

    override fun onCleared() {
        super.onCleared()
        log("onCleared viewmodel soura","viewmodel")
    }


}