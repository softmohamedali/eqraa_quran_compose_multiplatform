package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.tasbehat
import com.moali.eqraa.presentation.components.appcomponent.DisplayAlertDialog
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SebhaView(
    counter: String = "1000",
    isShowingRequestDialog:Boolean,
    currentTasbehaId:Int,
    onBackClick: () -> Unit,
    onRestartClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onBackStepClick: () -> Unit,
    onFloatingSebhaClick: () -> Unit,
    goSettingClick: () -> Unit,
    closeAlertClick: () -> Unit,
    onTasbehaItemClick:(Int)->Unit
) {
    val scrollableState = rememberScrollState()


    Scaffold(
        topBar = {
            TopAppbar(
                title = "Sebha",
                onBackClick = { onBackClick() },
                isBack = true
            )
        }
    ) {

        Box (
            modifier = Modifier.padding(it).fillMaxSize(),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    modifier = Modifier.weight(6.5f)
                ) {
                    items(tasbehat) { tasbeha ->
                        TasbheViewItem(
                            tasbeha=tasbeha,
                            onClick = onTasbehaItemClick,
                            isMarkable=tasbeha.id==currentTasbehaId
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().weight(3.5f).padding(12.dp)
                ) {
                    FloatingButton(
                        modifier = Modifier.weight(1f),
                        onClick = onFloatingSebhaClick
                    )
                    Spacer(Modifier.width(12.dp))
                    SebhaComponent(
                        modifier = Modifier.weight(1.5f).fillMaxHeight(),
                        counter = counter,
                        onRestartClick = onRestartClick,
                        onIncreaseClick = onIncreaseClick,
                        onBackStepClick = onBackStepClick,
                    )
                }
            }
            DisplayAlertDialog(
                showAlert = isShowingRequestDialog,
                title = stringResource(SharedRes.strings.enable_overlay_permission),
                text = stringResource(SharedRes.strings.dialog_enable_overlay_permission),
                negativeClick = closeAlertClick,
                positiveClick = goSettingClick,
                positiveActionText = stringResource(SharedRes.strings.go_to_settings),
                negativeActionText = stringResource(SharedRes.strings.back),
            )
        }

    }


}