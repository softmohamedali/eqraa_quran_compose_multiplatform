package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.runtime.Composable
import com.moali.eqraa.domain.models.Note


@Composable
fun HandleListTask(
    listNotes: List<Note>,
    navToTaskScreeen: (Int) -> Unit
) {
    if (listNotes.isEmpty()) {
        DisplayEmptyContent()
    } else {
        DisplayTasks(
            navToTaskScreeen = navToTaskScreeen,
            notes = listNotes
        )
    }
}