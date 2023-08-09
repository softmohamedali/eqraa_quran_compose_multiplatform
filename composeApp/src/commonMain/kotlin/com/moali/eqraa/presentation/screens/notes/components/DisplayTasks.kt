package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Note


@Composable
fun DisplayTasks(
    navToTaskScreeen: (Note) -> Unit,
    notes: List<Note>
) {
    LazyColumn (
        contentPadding = PaddingValues(8.dp)
            ){
        items(
            items = notes,
            key = { it.id.toString() },
        ) {
            NoteItem(note = it, navigateToTaskScreen = navToTaskScreeen)
        }
    }
}