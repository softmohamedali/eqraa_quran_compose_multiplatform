package com.moali.eqraa.core.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.moali.eqraa.ui.theme.DarkColorScheme
import com.moali.eqraa.ui.theme.LightColorScheme
import com.moali.eqraa.ui.theme.Typography

@Composable
actual fun EqraaTheme(
    darkTheme:Boolean,
    dynamicColor:Boolean,
    content:@Composable ()->Unit
){

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}