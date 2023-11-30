package com.moali.eqraa.presentation.screens.quran

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.core.utils.Constants.ARCHIVE_TYPE_SURA
import com.moali.eqraa.core.utils.Constants.ARCHIVE_TYPE_SURA_JUZA_KEY
import com.moali.eqraa.presentation.screens.juza.JuzaScreen
import com.moali.eqraa.presentation.screens.quran.component.QuranView
import com.moali.eqraa.presentation.screens.soura.SouraScreen
import com.russhwolf.settings.Settings
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class QuranScreen:Screen,KoinComponent{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val quranViewModel=getViewModel("QuranScreen", viewModelFactory { QuranViewModel() })
        val  settings: Settings by inject()
        QuranView(
            sour = quranViewModel.state.sour,
            ajza = quranViewModel.state.ajza,
            onBackClick = {
                navigator.pop()
            },
            onSouraItemClick = {
                navigator.push(SouraScreen(it.id))
            },
            onJuzaItemClick = {
                navigator.push(JuzaScreen(it.id))
            },
            onArchiveClick = {
                val destination=settings.getString(ARCHIVE_TYPE_SURA_JUZA_KEY,ARCHIVE_TYPE_SURA)
                if (destination==ARCHIVE_TYPE_SURA){
                    navigator.push(SouraScreen(null))
                }else{
                    navigator.push(JuzaScreen(null))
                }

            }
        )
    }


}