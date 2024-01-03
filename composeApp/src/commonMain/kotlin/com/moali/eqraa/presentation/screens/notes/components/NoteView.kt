package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moali.eqraa.core.shared.ui.AdmobBanner
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.ToolBarState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteView(
    navToTaskScreen: (Note?) -> Unit,
    scaffoldState: SnackbarHostState,
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
    onBackClick: () -> Unit
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
        snackbarHost={ SnackbarHost(scaffoldState) },
        content = {
            Box(
                modifier = Modifier.fillMaxSize().padding(it)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box (
                        modifier = Modifier.weight(1f)
                    ){
                        ListScreenContent(
                            navToTaskScreen,
                            notes,
                            searchTask = searchNotes,
                            toolBarState = toolBarState,
                            state = state,
                        )
                    }
                    AdmobBanner(modifier=Modifier.fillMaxWidth(),"ca-app-pub-7258529419894486/8313390660")
                }
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
                onBackClick=onBackClick
            )
        },
    )
}



