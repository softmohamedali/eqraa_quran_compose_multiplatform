package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.components.Center
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.eqraa.presentation.screens.soura.component.BottomAudioController
import com.moali.eqraa.presentation.screens.soura.component.MiniAudioPlayer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SebhaView(
    counter: String = "1000",
    onBackClick: () -> Unit,
    onRestartClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onBackStepClick:()->Unit
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
            modifier=Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.padding(it).width(250.dp).height(300.dp).padding(12.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topEnd = 80f,
                            topStart = 80f,
                            bottomEnd = 80f,
                            bottomStart = 80f
                        )
                    ).background(MaterialTheme.colorScheme.secondary)
                ,
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier=Modifier.padding(12.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topEnd = 40f,
                                topStart = 40f,
                                bottomEnd = 40f,
                                bottomStart = 40f
                            )
                        )
                        .background(MaterialTheme.colorScheme.onSurface)
                        .padding(8.dp)
                        .fillMaxWidth()
                    ,
                    text = counter,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surface
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text="BackOne",
                                color = MaterialTheme.colorScheme.surface
                            )
                            Icon(
                                modifier = Modifier.size(30.dp).clickable {
                                    onBackStepClick()
                                },
                                imageVector = Icons.Default.Circle,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.surface
                            )
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text="Restart",
                                color = MaterialTheme.colorScheme.surface
                            )
                            Icon(
                                modifier = Modifier.size(30.dp)
                                    .clickable {
                                               onRestartClick()
                                    },
                                imageVector = Icons.Default.Circle,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.surface
                            )
                        }
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            modifier = Modifier.size(120.dp).padding(12.dp)
                                .clickable {
                                    onIncreaseClick()
                                },
                            imageVector = Icons.Default.Circle,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }

                }
            }
        }

    }


}