package com.moali.eqraa.core.shared

import androidx.compose.runtime.Composable

@Composable
expect fun EqraaTheme(
    darkTheme:Boolean,
    dynamicColor:Boolean,
    content:@Composable ()->Unit
)