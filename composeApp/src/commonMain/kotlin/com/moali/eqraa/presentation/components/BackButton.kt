package com.moali.eqraa.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BackButton(
    onBackClick:()->Unit,
    backIcon:ImageVector=Icons.Rounded.ArrowBack
) {
    IconButton(
        onClick = {
            onBackClick()
        },
        content = {
            Icon(
                imageVector = backIcon,
                contentDescription = "back",
                tint = White
            )
        }
    )
}