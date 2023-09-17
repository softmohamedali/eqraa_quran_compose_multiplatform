package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.domain.models.Tasbeha
import com.moali.eqraa.presentation.components.MyLine
import com.moali.eqraa.ui.theme.second_text


@Composable
fun TasbheViewItem(
    tasbeha: Tasbeha,
    onClick: (Int) -> Unit,
    isMarkable:Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
            .border(
                3.dp,
                if (isMarkable) second_text else MaterialTheme.colorScheme.primary,
                RoundedCornerShape(10)
            )
            .padding(8.dp)
            .clickable {
                onClick(tasbeha.id)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tasbeha.name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(5.dp))
        Text(
            textAlign = TextAlign.End,
            text = tasbeha.useful,
            color = second_text,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

    }
}