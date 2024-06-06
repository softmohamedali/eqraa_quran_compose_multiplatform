package com.moali.eqraa.presentation.screens.compass

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.Resources
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class)
@Composable
fun CompassView(
    azimuth:Float,
    qiblaDirection:Double
) {

    val qiblaAzimuth = (qiblaDirection - azimuth + 360) % 360

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Al kobla",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = org.jetbrains.compose.resources.painterResource(Resources.images.compass),
                contentDescription = "Compass Needle",
                modifier = Modifier
                    .size(300.dp)
                    .rotate(-(qiblaAzimuth.toFloat()))
            )
        }
    }
}