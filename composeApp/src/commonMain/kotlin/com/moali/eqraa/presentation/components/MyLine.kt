package com.moali.eqraa.presentation.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyLine(
    padding:Float=0f,
    height:Float=1f,
    color: Color=Color.Black.copy(0.5f)
){
    Surface(
        modifier = Modifier.fillMaxWidth().height(height = height.dp),
        color = color
    ) {
    }
}