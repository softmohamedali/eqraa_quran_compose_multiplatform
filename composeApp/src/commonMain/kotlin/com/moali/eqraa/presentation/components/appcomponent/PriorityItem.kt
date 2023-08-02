package com.moali.eqraa.presentation.components.appcomponent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Priority


@Composable
fun PriorityItem(
    priority: Priority
){
    Row {
        Canvas(
            modifier = Modifier.size(20.dp)
        ){
            drawCircle(color = priority.color)
        }
        Text(
            text = priority.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

