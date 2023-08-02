package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.runtime.Composable
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.ToolBarState


@Composable
fun ListScreenContent(
    navToTaskScreeen: (Int) -> Unit,
    tasks: ResultState<List<Note>>,
    toolBarState: ToolBarState,
    searchTask: ResultState<List<Note>>,
    state: ResultState<Priority>?,
    highPriortyTasks: List<Note>,
    lowPriortyTasks: List<Note>,
) {
    if (state is ResultState.IsSucsses) {
        when {
            toolBarState == ToolBarState.TRIGER -> {
                if (searchTask is ResultState.IsSucsses) {
                    HandleListTask(listNotes = searchTask.data!!, navToTaskScreeen = navToTaskScreeen)
                }
            }
            state.data == Priority.NONE ->{
                if (tasks is ResultState.IsSucsses) {
                    HandleListTask(listNotes = tasks.data!!, navToTaskScreeen = navToTaskScreeen)
                }
            }
            state.data == Priority.LOW ->{
                HandleListTask(listNotes = lowPriortyTasks, navToTaskScreeen = navToTaskScreeen)
            }
            state.data == Priority.HIGH ->{
                HandleListTask(listNotes = highPriortyTasks, navToTaskScreeen = navToTaskScreeen)
            }
        }
    }
}