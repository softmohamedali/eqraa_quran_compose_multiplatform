package com.moali.eqraa.presentation.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.core.shared.utils.CompassSensorManager
import com.moali.eqraa.core.shared.utils.permission.AndroidPermissionCheck
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.presentation.screens.compass.CompassScreen
import com.moali.eqraa.presentation.screens.home.components.HomeEventsType
import com.moali.eqraa.presentation.screens.notes.NoteScreen
import com.moali.eqraa.presentation.screens.quran.QuranScreen
import com.moali.eqraa.presentation.screens.sebha.SebhaScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class HomeScreen(
) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val androidPermissionCheck: AndroidPermissionCheck by inject()

        val navigator = LocalNavigator.currentOrThrow
        val homeViewModel = getViewModel("homeViewMdoel", viewModelFactory { HomeViewModel() })
        HomeView(
            onItemClick = {
                when (it) {
                    HomeEventsType.TO_QURAN -> {
                        navigator.push(QuranScreen())
                    }

                    HomeEventsType.TO_NOTE -> {
                        navigator.push(NoteScreen(Action.NONE))
                    }

                    HomeEventsType.TO_SEBHA -> {
                        navigator.push(SebhaScreen())
                    }
                    HomeEventsType.TO_COMPASS -> {
                        if (androidPermissionCheck.isLocationPermissionGranted()){
                            navigator.push(CompassScreen())
                        }else{
                            androidPermissionCheck.openAppSettings()
                        }

                    }

                    else -> {}
                }
            },
            onShareClick = {
                homeViewModel.shareApp()
            },
            onSupportClick = {
                homeViewModel.isShowSupportDialog=true
            },
            onInfoClick = {},
            onMenuClick = {},
            isShowSupportDialog = homeViewModel.isShowSupportDialog,
            onShareSupportLinkClick = {
                homeViewModel.shareSupportLink()
            },
            onGoToSupportClick = {
                homeViewModel.openSupportLink()
            },
            onCloseSupportDialogClick = {
                homeViewModel.isShowSupportDialog=false
            }
        )
    }

}




