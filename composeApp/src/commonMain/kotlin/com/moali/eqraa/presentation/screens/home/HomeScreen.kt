package com.moali.eqraa.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.presentation.screens.home.components.HomeEventsType
import com.moali.eqraa.presentation.screens.notes.NoteScreen
import com.moali.eqraa.presentation.screens.quran.QuranScreen
import com.moali.eqraa.presentation.screens.sebha.SebhaScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.koin.core.component.KoinComponent


class HomeScreen(
):Screen, KoinComponent{

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val homeViewModel = getViewModel("homeViewMdoel", viewModelFactory { HomeViewModel() })
        HomeView(
            onItemClick = {
                when(it){
                    HomeEventsType.TO_QURAN -> {
                        navigator.push(QuranScreen())
                    }
                    HomeEventsType.TO_NOTE -> {
                        navigator.push(NoteScreen(Action.NONE))
                    }
                    HomeEventsType.TO_SEBHA -> {
                        navigator.push(SebhaScreen())
                    }

                    else -> {}
                }
            },
            onShareClick = {
                homeViewModel.shareApp()
            },
            onRateClick = {},
            onInfoClick = {},
            onMenuClick = {}
        )
    }

}




