package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SebhaComponent(
    counter:String,
    onRestartClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onBackStepClick:()->Unit,
    modifier: Modifier=Modifier
) {
    Column(
        modifier = modifier
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
            modifier= Modifier.padding(12.dp)
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
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
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

            }


            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    modifier = Modifier
                        .size(100.dp).height(70.dp).padding(12.dp)
                        .clip(RoundedCornerShape(40.dp))
                        ,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.surface,
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    content = {
                    },
                    onClick = { onIncreaseClick() }
                )
            }

        }
    }
}