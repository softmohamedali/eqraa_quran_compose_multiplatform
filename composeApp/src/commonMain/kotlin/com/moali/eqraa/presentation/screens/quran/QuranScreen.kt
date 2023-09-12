package com.moali.eqraa.presentation.screens.quran

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.presentation.screens.quran.component.QuranView
import com.moali.eqraa.presentation.screens.soura.SouraScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

class QuranScreen:Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val quranViewModel=getViewModel("QuranScreen", viewModelFactory { QuranViewModel() })

        QuranView(
            sour = quranViewModel.state.sour,
            onBackClick = {
                navigator.pop()
            },
            onSouraItemClick = {
                navigator.push(SouraScreen(it.id))
            }
        )
    }


}