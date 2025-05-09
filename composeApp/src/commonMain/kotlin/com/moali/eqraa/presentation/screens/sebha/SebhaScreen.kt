package com.moali.eqraa.presentation.screens.sebha

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.presentation.screens.sebha.component.SebhaView
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory


class SebhaScreen(
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val sebhaViewModel = getViewModel("SebhaViewModel", viewModelFactory { SebhaViewModel() })
        val state = sebhaViewModel.state


        SebhaView(
            counter = state.counter.toString(),
            isShowingRequestDialog = state.showingRequestDialog,
            currentTasbehaId=state.currentTasbehaId,
            onBackClick = {
                navigator.pop()
            },
            onRestartClick = {
                sebhaViewModel.onEvent(SebhaEvents.OnRestartClick)
            },
            onIncreaseClick = {
                sebhaViewModel.onEvent(SebhaEvents.OnIncreaseClick)
            },
            onBackStepClick = {
                sebhaViewModel.onEvent(SebhaEvents.OnBackStepClick)
            },
            onFloatingSebhaClick={
                sebhaViewModel.onEvent(SebhaEvents.OnFloatingSebhaClick)
            },
            goSettingClick = {
                sebhaViewModel.onEvent(SebhaEvents.OnGoSettingClick)
            },
            closeAlertClick = {
                sebhaViewModel.onEvent(SebhaEvents.OnCloseAlertClick)
            },
            onTasbehaItemClick = {
                sebhaViewModel.onEvent(SebhaEvents.OnTasbehaItemClick(it))
            }
        )
    }


}