package com.moali.eqraa.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.utils.MainCompnentAction
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.screens.home.components.HomeEventsType
import com.moali.eqraa.presentation.screens.notes.NoteScreen
import com.moali.eqraa.presentation.screens.quran.QuranScreen
import com.moali.eqraa.presentation.screens.sebha.SebhaScreen
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class HomeScreen(
//    private val noteDataSource: NoteDataSource?
):Screen, KoinComponent{

    private val servicesUtils: ServicesUtils by inject()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scop= rememberCoroutineScope()
        LaunchedEffect(1){
            QuranJsonRecourse().getQuranFromResources()
        }
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
                scop.launch {
                    servicesUtils.startMainComponentAction(MainCompnentAction.SHARE_APP)
                }
            },
            onRateClick = {},
            onInfoClick = {},
            onMenuClick = {}
        )
    }

}




