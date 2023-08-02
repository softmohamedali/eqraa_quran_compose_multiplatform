package com.moali.eqraa.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFullW(
    text:String,
    modifier: Modifier=Modifier,
    fontSize: TextUnit = 12.sp,
){
    val myModifier=modifier.fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
        .padding(10.dp)

    Text(
        text = text,
        fontSize = fontSize,
        modifier = myModifier,
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onPrimary

    )
}











