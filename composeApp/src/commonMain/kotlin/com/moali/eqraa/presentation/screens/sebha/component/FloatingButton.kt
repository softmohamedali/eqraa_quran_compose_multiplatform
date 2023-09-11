package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FloatingButton(
    onClick:()->Unit,
    modifier: Modifier=Modifier
) {
    Button(
        modifier= modifier
            .clip(
                shape = RoundedCornerShape(
                    topEnd = 80f,
                    topStart = 80f,
                    bottomEnd = 80f,
                    bottomStart = 80f
                )
            ).background(MaterialTheme.colorScheme.secondary),
        onClick = {
           onClick()
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
}