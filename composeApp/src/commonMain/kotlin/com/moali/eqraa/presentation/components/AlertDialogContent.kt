package com.moali.eqraa.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AlertDialogContent(
    showAlert: Boolean,
    content:@Composable ()->Unit,
) {
    if (showAlert) {
        Box (
            modifier = Modifier.fillMaxSize()
                .clickable {  }
                .background(Color.Black.copy(0.5f)),
            contentAlignment = Alignment.Center,

        ){
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                content()
            }
        }
    }
}