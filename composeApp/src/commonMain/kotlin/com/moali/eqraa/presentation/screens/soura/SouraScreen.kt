package com.moali.eqraa.presentation.screens.soura

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.screens.quran.QuranView
import com.moali.eqraa.presentation.screens.quran.QuranViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory


class SouraScreen(
    private val soura:Soura
): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        SouraView(
            soura = soura,
            onBackClick = {
                navigator.pop()
            },
        )
    }


}