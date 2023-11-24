package com.moali.eqraa.presentation.screens.soura

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.utils.Constants.ARCHIVE_SCROLL_POSITION_KEY
import com.moali.eqraa.core.utils.Constants.ARCHIVE_SOURA_NAME_KEY
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.di.DIManualAppModule
import com.moali.eqraa.domain.abstractions.media.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.media.MediaPlayerOperation
import com.moali.eqraa.domain.usecases.GetQuranUseCase
import com.russhwolf.settings.Settings
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SouraViewModel(
    private val getQuranUseCase: GetQuranUseCase = DIManualAppModule.getQuranUseCase,
) : ViewModel(), KoinComponent {

    private val mediaPlayerController: MediaPlayerOperation by inject()
    private val dispatchers: Dispatchers by inject()
    private val servicesUtils: ServicesUtils by inject()
    private val  settings: Settings by inject()
    var state by mutableStateOf(SouraState())



    fun onEvent(events: SouraEvents) {
        when (events) {
            is SouraEvents.OnInit -> {
                state = state.copy(
                    souraId = events.souraId,
                    isLoadingMedia = true,
                    isLoading = true
                )
                getQuran()
            }
            is SouraEvents.OnGetArchive->{
                state = state.copy(
                    souraId = settings.getInt(ARCHIVE_SOURA_NAME_KEY,1),
                    isLoadingMedia = true,
                    isLoading = true
                )
                getQuranScrollPostion()
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

            is SouraEvents.OnAddReferenceClick->{
                settings.putInt(ARCHIVE_SCROLL_POSITION_KEY,events.scrollValue)
                settings.putInt(ARCHIVE_SOURA_NAME_KEY,events.souraId)
            }

        }
    }

    private fun getQuranScrollPostion() {
        viewModelScope.launch(dispatchers.main) {
            getQuranUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(
                            soura = it.data!![state.souraId-1],
                            scrollPotion = settings.getInt(ARCHIVE_SCROLL_POSITION_KEY,0),
                            isLoading = false
                        )
                        prepere()
                        mediaPlayerController.playPauseState.collect{
                            state=state.copy(isPlay = it)
                        }
                    }
                    it is ResultState.IsLoading ->{

                    }
                    it is ResultState.IsError ->{

                    }
                }

            }
        }
    }

    private fun getQuran(){
        viewModelScope.launch(dispatchers.main) {
            getQuranUseCase().collect{
                when{
                    it is ResultState.IsSucsses ->{
                        state=state.copy(
                            soura = it.data!![state.souraId-1],
                            isLoading = false
                        )
                        prepere()
                        mediaPlayerController.playPauseState.collect{
                            state=state.copy(isPlay = it)
                        }
                    }
                    it is ResultState.IsLoading ->{

                    }
                    it is ResultState.IsError ->{

                    }
                }

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
            servicesUtils.startServiceIntentToCreatePlayerNotification(
                souraName = state.soura.name,
                shehkName = "ElShehk Mashary Afisi"
            )
        } else {
            mediaPlayerController.pause()

        }
    }


    private fun prepere() {
        mediaPlayerController
            .prepare("https://server8.mp3quran.net/afs/${countNumberDigets(state.soura.id)}.mp3",
                listener = object : MediaPlayerListener {
                    override fun onReady() {
                        state=state.copy(
                            totalTime = convertMillisecondsToTime(
                                mediaPlayerController.getDuration()
                            ),
                            totalProgress = mediaPlayerController.getDuration()?.toFloat()?:0f,
                            isLoadingMedia = false
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
                        state=state.copy(isLoadingMedia = false)
                    }

                    override fun onError() {
                        state=state.copy(isLoadingMedia = false)
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




}