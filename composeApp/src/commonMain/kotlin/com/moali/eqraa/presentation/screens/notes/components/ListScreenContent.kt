package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.runtime.Composable
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.ToolBarState


@Composable
fun ListScreenContent(
    navToTaskScreen: (Note) -> Unit,
    tasks: List<Note>,
    toolBarState: ToolBarState,
    searchTask: List<Note>,
    state: Priority?,
) {

    when {
        toolBarState == ToolBarState.TRIGER -> {
            HandleListTask(listNotes = searchTask, navToTaskScreeen = navToTaskScreen)

        }

        else -> {
            HandleListTask(listNotes = tasks, navToTaskScreeen = navToTaskScreen)
        }


    }
}