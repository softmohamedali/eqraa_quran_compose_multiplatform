package com.moali.eqraa.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.eqraa.presentation.screens.home.components.EqraaIteem
import com.moali.eqraa.presentation.screens.home.components.HomeEventsType
import com.moali.eqraa.presentation.screens.home.components.SupportDialog
import com.moali.eqraa.ui.resources.ContentDescriptions
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView (
    isShowSupportDialog:Boolean,
    onItemClick:(HomeEventsType)->Unit={},
    onInfoClick:()->Unit={},
    onShareClick:()->Unit={},
    onShareSupportLinkClick:()->Unit={},
    onGoToSupportClick:()->Unit={},
    onSupportClick:()->Unit={},
    onCloseSupportDialogClick:()->Unit={},
    onMenuClick:()->Unit={},
){

    Scaffold(
        topBar = {
            TopAppbar(
                title = stringResource(SharedRes.strings.app_name),
                isBack = false,
                actions = {
                    IconButton(
                        onClick = {onSupportClick()},
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.Money,
                                contentDescription = ContentDescriptions.INFO,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                    IconButton(
                        onClick = {onShareClick()},
                        content = {
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                contentDescription = ContentDescriptions.INFO,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    )
                }
            )

        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize().padding(it)
            ){
                Column(
                    modifier = Modifier.fillMaxSize().padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    EqraaIteem(
                        text = stringResource(SharedRes.strings.alquran_alkarem),
                        onClick = {
                            onItemClick(HomeEventsType.TO_QURAN)
                        },
                        icon = painterResource(SharedRes.images.open_quran)
                    )
                    EqraaIteem(
                        text = stringResource(SharedRes.strings.sebha),
                        onClick = {
                            onItemClick(HomeEventsType.TO_SEBHA)
                        },
                        icon = painterResource(SharedRes.images.sebha),
                    )
                    EqraaIteem(
                        text = stringResource(SharedRes.strings.note),
                        onClick = {
                            onItemClick(HomeEventsType.TO_NOTE)
                        },
                        icon = painterResource(SharedRes.images.note)
                    )

                }
                SupportDialog(
                    isShow = isShowSupportDialog,
                    onCloseClick = onCloseSupportDialogClick,
                    onShareClick=onShareSupportLinkClick,
                    onGoToSupport = onGoToSupportClick
                )
            }
        }
    )

}