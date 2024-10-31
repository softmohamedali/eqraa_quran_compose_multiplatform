package com.moali.eqraa.presentation.screens.compass

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.Resources
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CompassView(
    azimuth:Float,
    qiblaDirection:Double,
    onBackClick:()->Unit
) {

    val qiblaAzimuth = (qiblaDirection - azimuth + 360) % 360

    Scaffold(
        topBar = {
            TopAppbar(
                title = stringResource(SharedRes.strings.al_quibla),
                onBackClick = { onBackClick() },
                isBack = true
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(SharedRes.strings.Al_Qiblah_Direction),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                    Canvas(modifier = Modifier.size(100.dp)) {
                        drawCircle(
                            color = Color.Red,
                            radius = 20f
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
                Icon(
                    painter = painterResource(SharedRes.images.qibla),
                    contentDescription = "Compass Needle",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(300.dp)
                        .rotate(-(qiblaAzimuth.toFloat()))
                )
            }
        }
    }
}