package com.moali.eqraa.presentation.components.appcomponent

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SwipeableTap() {

}




data class TapItem(
    val title:String,
    val unSelectedIcon:ImageVector,
    val selectedIcon:ImageVector,
)