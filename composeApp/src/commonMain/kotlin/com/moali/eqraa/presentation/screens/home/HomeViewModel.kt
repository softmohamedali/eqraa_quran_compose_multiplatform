package com.moali.eqraa.presentation.screens.home

import com.moali.eqraa.core.shared.services.ServicesUtils
import com.moali.eqraa.core.shared.utils.Dispatchers
import com.moali.eqraa.core.utils.MainCompnentAction
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class HomeViewModel : ViewModel(), KoinComponent {

    private val servicesUtils: ServicesUtils by inject()

    fun shareApp(){
        viewModelScope.launch {
            servicesUtils.startMainComponentAction(MainCompnentAction.SHARE_APP)
        }
    }
}