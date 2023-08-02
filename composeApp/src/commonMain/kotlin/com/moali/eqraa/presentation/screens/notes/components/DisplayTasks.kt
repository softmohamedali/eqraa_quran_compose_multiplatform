package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.presentation.components.appcomponent.NoteItem


@Composable
fun DisplayTasks(
    navToTaskScreeen: (Int) -> Unit,
    notes: List<Note>
) {
    LazyColumn {
        items(items = notes, key = { it.id }) {
            NoteItem(note = it, navigateToTaskScreen = navToTaskScreeen)
        }
    }
}