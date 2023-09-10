package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SebhaView(
    counter: String = "1000",
    onBackClick: () -> Unit,
    onRestartClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onBackStepClick: () -> Unit,
    onFloatingSebhaClick: () -> Unit,
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
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sebha")
                    Icon(
                        modifier = Modifier.clickable {
                            onFloatingSebhaClick()
                        },
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = null
                    )
                }
                FloatingSebhaContent {  }
            }

            SebhaComponent(
                counter = counter,
                onRestartClick = onRestartClick,
                onIncreaseClick = onIncreaseClick,
                onBackStepClick = onBackStepClick,
            )
        }

    }


}