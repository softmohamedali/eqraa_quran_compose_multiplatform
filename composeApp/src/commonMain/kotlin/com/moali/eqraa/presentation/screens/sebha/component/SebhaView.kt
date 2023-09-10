package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Row(
                modifier = Modifier.fillMaxWidth().height(300.dp).padding(12.dp)
            ) {
                Button(
                    modifier=Modifier.weight(1f)
                        .clip(
                            shape = RoundedCornerShape(
                                topEnd = 80f,
                                topStart = 80f,
                                bottomEnd = 80f,
                                bottomStart = 80f
                            )
                        ).background(MaterialTheme.colorScheme.secondary),
                    onClick = {
                        onFloatingSebhaClick()
                    },
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                "Floating Seb7a",
                                maxLines = 2,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                            Icon(
                                modifier = Modifier.size(60.dp),
                                imageVector = Icons.Default.Fullscreen,
                                contentDescription = null
                            )
                        }
                    }
                )
                Spacer(Modifier.width(12.dp))
                SebhaComponent(
                    modifier=Modifier.weight(1.5f).fillMaxHeight(),
                    counter = counter,
                    onRestartClick = onRestartClick,
                    onIncreaseClick = onIncreaseClick,
                    onBackStepClick = onBackStepClick,
                )
            }

        }

    }


}