package com.moali.eqraa.presentation.screens.notes

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.ToolBarState
import com.moali.eqraa.presentation.screens.notes.components.FabListScreen
import com.moali.eqraa.presentation.screens.notes.components.ListScreenContent
import com.moali.eqraa.presentation.screens.notes.components.ShowSnakBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(
    navToTaskScreeen: (Int) -> Unit,
    noteViewModel: NoteViewModel,
    scaffoldState:ScaffoldState,
) {

    ShowSnakBar(
        scaffoldState = scaffoldState,
        title = "noteViewModel.title.value",
        action = Action.NONE,//action
        handleDBAction = {
//            noteViewModel.handleDBAction(action)
        },
        undoDelteTask = {
//            noteViewModel.action.value = Action.UNDO
        }
    )
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
//            ListScreenContent(
//                navToTaskScreeen,
//                tasks,
//                searchTask = searchTasks,
//                toolBarState = toolBarState,
//                state = state,
//                highPriortyTasks = highPriorityTasks,
//                lowPriortyTasks = lowPriorityTasks
//            )
            ListScreenContent(
                navToTaskScreeen,
                ResultState.IsLoading,//tasks,
                searchTask = ResultState.IsLoading,
                toolBarState = ToolBarState.TRIGER,
                state = ResultState.IsLoading,
                highPriortyTasks = emptyList(),
                lowPriortyTasks = emptyList()
            )
        },
        floatingActionButton = { FabListScreen(navToTaskScreeen) },
        topBar = {
//            ToolBarListScreen(
//                sharedViewModel = noteViewModel,
//                toolBarSearchValue = toolBarSearchValue,
//                toolBarState = toolBarState
//            )
        },
    )
}