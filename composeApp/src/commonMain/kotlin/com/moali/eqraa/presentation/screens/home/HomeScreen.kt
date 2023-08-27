package com.moali.eqraa.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.data.resource.QuranJsonRecourse
import com.moali.eqraa.domain.abstractions.NoteDataSource
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.presentation.screens.home.components.HomeEventsType
import com.moali.eqraa.presentation.screens.notes.NoteScreen
import com.moali.eqraa.presentation.screens.quran.QuranScreen


class HomeScreen(
//    private val noteDataSource: NoteDataSource?
):Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
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

                    else -> {}
                }
            }
        )
    }

}




