package com.moali.eqraa.core.shared

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.moali.eqraa.ui.theme.DarkColorScheme
import com.moali.eqraa.ui.theme.LightColorScheme
import com.moali.eqraa.ui.theme.Typography

@Composable
actual fun EqraaTheme(
    darkTheme:Boolean,
    dynamicColor:Boolean,
    content:@Composable ()->Unit
){
    val colorSchema=when{
        dynamicColor && Build.VERSION.SDK_INT>=Build.VERSION_CODES.S ->{
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme

        else -> LightColorScheme
    }

    val view = LocalView.current
    if(!view.isInEditMode) {
        SideEffect {
            //to make status bar work with dynamic color of material 3
            val window=(view.context as Activity).window
            window.statusBarColor=colorSchema.primary.toArgb()
            // to handle the color in the status bar to suit if is dark or light
            WindowCompat.getInsetsController(window,view).isAppearanceLightStatusBars=darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorSchema,
        typography = Typography,
        content = content
    )
}