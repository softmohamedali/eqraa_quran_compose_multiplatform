package com.moali.eqraa.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Note
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.presentation.screens.home.components.EqraaIteem
import com.moali.eqraa.presentation.screens.home.components.HomeEventsType
import com.moali.eqraa.presentation.screens.home.components.HomeTopAppBar
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun HomeView (
    onItemClick:(HomeEventsType)->Unit={},
    infoClick:()->Unit={},
    shareClick:()->Unit={},
    rateClick:()->Unit={},
    menuClick:()->Unit={},
){

    Column (
        modifier = Modifier.fillMaxSize(),
    ){
        HomeTopAppBar(
            modifier = Modifier.weight(1f)
        )
        Column(
            modifier = Modifier.weight(10f).padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            EqraaIteem(
                text = stringResource(SharedRes.strings.alquran_alkarem),
                onClick = {
                    onItemClick(HomeEventsType.TO_QURAN)
                },
                icon = painterResource(SharedRes.images.open_quran)
            )
//            EqraaIteem(
//                text = "Seb7a",
//                onClick = {
//                    onItemClick(HomeEventsType.TO_SEBHA)
//                }
//            )
            EqraaIteem(
                text = stringResource(SharedRes.strings.note),
                onClick = {
                    onItemClick(HomeEventsType.TO_NOTE)
                },
               icon = Icons.Default.Note
            )

        }
    }

}