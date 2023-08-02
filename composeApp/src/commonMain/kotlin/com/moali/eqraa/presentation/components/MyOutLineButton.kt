package com.moali.eqraa.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun MyOutLineButton(
    text:String="",
    width:Float=150f,
    modifier: Modifier=Modifier.width(width.dp),
    textColor:Color= MaterialTheme.colorScheme.primary,
    backgroundColor: Color=MaterialTheme.colorScheme.onBackground,
    onclick:()->Unit,
){
    OutlinedButton(
        modifier =modifier,
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            contentColor =backgroundColor
        ),
        border = BorderStroke(1.dp, textColor),
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor
            )
        )
    }
}













