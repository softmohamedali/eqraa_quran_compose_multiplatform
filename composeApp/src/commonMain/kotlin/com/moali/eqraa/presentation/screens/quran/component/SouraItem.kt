package com.moali.eqraa.presentation.screens.quran.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Soura

@Composable
fun SouraItem(
    soura:Soura,
    onClick:()->Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth().height(70.dp).padding(8.dp)
            .border(1.dp,MaterialTheme.colorScheme.primary,RoundedCornerShape(3))
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box (
            modifier = Modifier.size(50.dp).weight(1f),
            contentAlignment = Alignment.Center,
        ){
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.Circle,
                //ToDo content discription
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = soura.id.toString(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Text(
            modifier = Modifier.weight(8f),
            textAlign = TextAlign.End,
            text = soura.name
        )

    }
}