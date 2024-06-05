package com.moali.eqraa.presentation.screens.juza

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory


class JuzaScreen(
    private val juzaId:Int?
): Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val juzaViewModel=getViewModel("SouraViewModel", viewModelFactory { JuzaViewModel() })
        val state=juzaViewModel.state
        LaunchedEffect(1){
            if(juzaId!=null){
                juzaViewModel.onEvent(JuzaEvents.OnInit(juzaId))
            }else{
                juzaViewModel.onEvent(JuzaEvents.OnGetArchive)
            }

        }



        JuzaView(
            isLoading=state.isLoading,
            juza = state.juza,
            onBackClick = {
                navigator.pop()
            },
            lang= state.lang,
            scrollPostion=state.scrollPotion,
            juzaMap = state.juzaMap,
            onAddReferenceClick={ scrollValue,juzaId ->
                juzaViewModel.onEvent(
                    JuzaEvents.OnAddReferenceClick(scrollValue = scrollValue, juzaId = juzaId)
                )
            }
        )
    }


}