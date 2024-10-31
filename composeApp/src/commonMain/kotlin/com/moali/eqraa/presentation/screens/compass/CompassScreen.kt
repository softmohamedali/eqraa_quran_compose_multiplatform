package com.moali.eqraa.presentation.screens.compass

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.presentation.screens.sebha.SebhaEvents
import com.moali.eqraa.presentation.screens.sebha.SebhaViewModel
import com.moali.eqraa.presentation.screens.sebha.component.SebhaView
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory


class CompassScreen(
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val compassViewModel = getViewModel("CompassViewModel", viewModelFactory { CompassViewModel() })
        val azimuth by compassViewModel.azimuth.collectAsState(0f)
        val qiblaDirection by compassViewModel.qiblaDirection.collectAsState(0.0)


        CompassView(
            azimuth=azimuth,
            qiblaDirection=qiblaDirection,
            onBackClick = {
                navigator.pop()
            }
        )
    }


}