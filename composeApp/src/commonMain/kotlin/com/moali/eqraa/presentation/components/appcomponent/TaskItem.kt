package com.moali.eqraa.presentation.components.appcomponent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.domain.models.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    navigateToTaskScreen:(Int)-> Unit
){
    Surface(
        onClick = {
        navigateToTaskScreen(note.id)
        },
        modifier = Modifier
            .fillMaxWidth()
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
                        drawCircle(color = note.priority.color)
                    }
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

