package com.moali.eqraa.presentation.screens.soura.component

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AnimatedText(
    text:String,
    modifier:Modifier=Modifier
) {
    val scrollState = rememberScrollState()
    var shouldAnimate by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = shouldAnimate){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(10000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        shouldAnimate=!shouldAnimate
    }
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 20.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = modifier.horizontalScroll(scrollState, false)
            .fillMaxSize().padding(horizontal = 10.dp)
    )
}