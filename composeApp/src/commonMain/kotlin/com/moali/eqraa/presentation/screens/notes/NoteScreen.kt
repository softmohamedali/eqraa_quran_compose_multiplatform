package com.moali.eqraa.presentation.screens.notes

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import com.moali.eqraa.domain.abstractions.NoteDataSource
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.presentation.screens.note_details.NoteDetailsScreen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory


class NoteScreen(
    private val noteDataSource: NoteDataSource,
    private val action: Action?
) : Screen {

    @Composable
    override fun Content() {
        val noteViewModel = getViewModel(
            "NoteScreen",
            viewModelFactory { NoteViewModel(noteDataSource = noteDataSource) }
        )

        val state = noteViewModel.state
        if (action != null) {
            noteViewModel.handleDBAction(action)
        }

        val scaffoldState = rememberScaffoldState()
        val navigator = LocalNavigator.currentOrThrow


        NoteView(
            navToTaskScreen = {
                navigator.push(
                    NoteDetailsScreen(
                        noteDataSource,
                        it,
                    )
                )
            },
            scaffoldState = scaffoldState,
            action = state.action,
            notes = state.allNotes,
            searchNotes = state.searchNotes,
            state = state.priority,
            toolBarState = state.noteToolBarState,
            toolBarSearchValue = state.toolbarSearchValue,
            isPriorityMenuShow = state.isPriorityMenuShown,
            isActionMenuShown = state.isActionMenuShown,
            onHandleDBAction = {
                noteViewModel.onEvent(NoteEvents.OnHandleDBAction)
            },
            onUndoDeleteTask = {
                noteViewModel.onEvent(NoteEvents.OnUndoDeleteTask)
            },
            onPriorityClickSaveState = {
                Logger.i { "prioirty.${it}" }
                noteViewModel.onEvent(NoteEvents.OnPriorityClickSaveState(it))
            },
            onActionMenuClick = {
                noteViewModel.onEvent(NoteEvents.OnActionMenuClick)
            },

            onConfirmDialogClick = {
                noteViewModel.onEvent(NoteEvents.OnConfirmDialogClick)
            },
            onSearchOpenClick = {
                noteViewModel.onEvent(NoteEvents.OnSearchOpenClick)
            },
            onFilterClick = {
                noteViewModel.onEvent(NoteEvents.OnFilterClick)
            },
            onSearchClick = {
                noteViewModel.onEvent(NoteEvents.OnSearchClick)
            },
            onCloseClick = {
                noteViewModel.onEvent(NoteEvents.OnCloseClick)
            },
            onSearchTextChange = {
                noteViewModel.onEvent(NoteEvents.OnSearchTextChange(it))
            },
            isShowAlertDialog=state.isShowDeleteDialog,
            onCloseDialog={
                noteViewModel.onEvent(NoteEvents.OnCloseAlertDelete)
            },
            onDeleteActionClick = {
                noteViewModel.onEvent(NoteEvents.OnDeleteAllClick)
            }
        )

    }


}



