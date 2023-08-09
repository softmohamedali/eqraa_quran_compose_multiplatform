package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.getPropertyColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    navigateToTaskScreen:(Note)-> Unit
){
    Card (
        onClick = {
        navigateToTaskScreen(note)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp)
                ){
            Row(
            ){
                Text(
                    text = note.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(8f)
                )
                Box (
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.TopEnd
                ){
                    Canvas(modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)){
                        drawCircle(getPropertyColor(note.priority))
                    }
                    Logger.i { "note Priorty in item ${note.priority}" }
                }
            }
            Text(
                text = note.content,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }

    }
}

