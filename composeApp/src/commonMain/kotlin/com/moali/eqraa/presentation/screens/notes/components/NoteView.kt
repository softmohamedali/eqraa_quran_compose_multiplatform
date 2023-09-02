package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.ToolBarState
import com.moali.eqraa.presentation.screens.notes.components.FabListScreen
import com.moali.eqraa.presentation.screens.notes.components.ListScreenContent
import com.moali.eqraa.presentation.screens.notes.components.OthersMenu
import com.moali.eqraa.presentation.screens.notes.components.PriorityMenu
import com.moali.eqraa.presentation.screens.notes.components.ShowSnakBar
import com.moali.eqraa.presentation.screens.notes.components.ToolBarListScreen


@Composable
fun NoteView(
    navToTaskScreen: (Note?) -> Unit,
    scaffoldState: ScaffoldState,
    action: Action,
    notes: List<Note>,
    searchNotes: List<Note>,
    state: Priority?,
    toolBarState: ToolBarState,
    toolBarSearchValue: String,
    isPriorityMenuShow: Boolean,
    onHandleDBAction: () -> Unit,
    onUndoDeleteTask: () -> Unit,
    onPriorityClickSaveState: (Priority) -> Unit,
    isActionMenuShown: Boolean,
    onDeleteActionClick: () -> Unit,
    onActionMenuClick:()->Unit,
    onConfirmDialogClick: () -> Unit,
    onSearchOpenClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCloseClick: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    isShowAlertDialog: Boolean,
    onCloseDialog: () -> Unit,
) {


    ShowSnakBar(
        scaffoldState = scaffoldState,
        title = "noteViewModel.title.value",
        action = action,
        handleDBAction = {
            onHandleDBAction()
        },
        undoDelteTask = {
            onUndoDeleteTask()

        }
    )
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                ListScreenContent(
                    navToTaskScreen,
                    notes,
                    searchTask = searchNotes,
                    toolBarState = toolBarState,
                    state = state,
                )
                if (isPriorityMenuShow) {
                    PriorityMenu(
                        onClick = {
                            onPriorityClickSaveState(it)
                        },
                        modifier = Modifier.align(Alignment.TopEnd),
                        showNone = true
                    )
                }
                if (isActionMenuShown) {
                    OthersMenu(
                        onDeleteClick = {
                            onDeleteActionClick()
                        },
                        modifier = Modifier.align(Alignment.TopEnd)
                    )
                }

            }

        },
        floatingActionButton = { FabListScreen(navToTaskScreen) },
        topBar = {
            ToolBarListScreen(
                toolBarSearchValue = toolBarSearchValue,
                toolBarState = toolBarState,
                onConfirmDialogClick = onConfirmDialogClick,
                onSearchOpenClick = onSearchOpenClick,
                onFilterClick = onFilterClick,
                onSearchClick = onSearchClick,
                onCloseClick = onCloseClick,
                onActionMenuClick=onActionMenuClick,
                onSearchTextChange = onSearchTextChange,
                isShowAlertDialog = isShowAlertDialog,
                onCloseDialog = onCloseDialog,
            )
        },
    )
}