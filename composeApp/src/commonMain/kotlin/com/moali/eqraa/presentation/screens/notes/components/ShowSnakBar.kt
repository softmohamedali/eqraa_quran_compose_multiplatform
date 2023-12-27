package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.material.ScaffoldState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.moali.eqraa.domain.models.Action
import kotlinx.coroutines.launch

@Composable
fun ShowSnakBar(
    scaffoldState: SnackbarHostState,
    action: Action,
    title: String,
    undoDelteTask: (Action) -> Unit,
    handleDBAction: () -> Unit
) {
    handleDBAction()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action)
    {
        if (action != Action.NONE) {
            scope.launch {
                val result = scaffoldState.showSnackbar(
                    message = massageText(
                        action = action,
                        title = title
                    ),
                    actionLabel = labelText(action = action)
                )
                undoTask(
                    action = action,
                    snackbarResult = SnackbarResult.ActionPerformed,//result,
                    undoTask = undoDelteTask
                )
            }
        }
    }

}

fun massageText(action: Action, title: String): String {
    return if (action == Action.DELETE_ALL) "All Tasks Deleted" else "${action.name} : ${title}"
}

fun labelText(action: Action): String {
    return if (action == Action.DELETE) "UNDO" else "OK"
}

fun undoTask(
    action: Action,
    undoTask: (Action) -> Unit,
    snackbarResult: SnackbarResult

) {
    if (action == Action.DELETE && snackbarResult == SnackbarResult.ActionPerformed) {
        undoTask(Action.UNDO)
    }
}