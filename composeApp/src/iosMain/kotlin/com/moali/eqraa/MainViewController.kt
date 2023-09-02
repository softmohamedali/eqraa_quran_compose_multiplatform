package com.moali.eqraa

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController


fun MainViewController() =ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
    MyApp(
        darkTheme = isDarkTheme,
        dynamicColor = false,
    )
}