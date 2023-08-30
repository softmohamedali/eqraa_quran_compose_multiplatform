package com.moali.eqraa.presentation.screens.soura

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.OnBackPressed
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.domain.models.Soura
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory


class SouraScreen(
    private val soura:Soura
): Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val souraViewModel=getViewModel("SouraViewModel", viewModelFactory { SouraViewModel() })
        val state=souraViewModel.state
        LaunchedEffect(1){
            souraViewModel.onEvent(SouraEvents.OnInit(soura))
        }


        SouraView(
            soura = soura,
            onBackClick = {
                navigator.pop()
            },
            isAudioPlayed = state.isPlay,
            currentProgress = state.currentAudioProgress,
            totalProgress=state.totalProgress,
            currentTime = state.currentTime,
            totalTime = state.totalTime,
            isShowBottomSheet=state.isShowBottomAudioSheet,
            onItemClick = {
                souraViewModel.onEvent(SouraEvents.OnAudioMiniClick)
            },
            onPreviousClick = {
                souraViewModel.onEvent(SouraEvents.OnPreviousClick)
            },
            onPauseClick = {
                souraViewModel.onEvent(SouraEvents.OnPauseClick)
            },
            onResumeClick = {
                souraViewModel.onEvent(SouraEvents.OnResumeClick)
            },
            onNextClick = {
                souraViewModel.onEvent(SouraEvents.OnNextClick)
            },
            onSliderChange = {
                souraViewModel.onEvent(SouraEvents.OnSeekChange(it))
            },
            onSliderChangeFinished = {
                souraViewModel.onEvent(SouraEvents.OnSeekFinishedChange)
            },
            onRewind = {
                souraViewModel.onEvent(SouraEvents.OnTeenBackWardClick)
            },
            onForward = {
                souraViewModel.onEvent(SouraEvents.OnTeenForwardClick)
            },
            onClose = {
                souraViewModel.onEvent(SouraEvents.OnCloseBottomSheetClick)
            }
        )
    }


}