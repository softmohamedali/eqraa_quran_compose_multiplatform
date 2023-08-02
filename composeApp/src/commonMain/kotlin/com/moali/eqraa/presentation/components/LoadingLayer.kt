package com.moali.eqraa.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun LoadingLayer(
    color: Color =MaterialTheme.colorScheme.onPrimary,
    contentDescription:String=""
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = color
    ) {
        Center {
            CircularProgressIndicator(
                modifier = Modifier.semantics {
                     this.contentDescription=contentDescription
                },
                color = color
            )
        }
    }
}