package com.moali.eqraa.presentation.screens.note_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.getPropertyFromString
import com.moali.eqraa.presentation.screens.note_details.components.TaskToolbar
import com.moali.eqraa.presentation.components.appcomponent.DisplayAlertDialog
import com.moali.eqraa.presentation.screens.note_details.components.NoteDetailsView
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.stringResource


class NoteDetailsScreen(
//    private val noteDataSource: NoteDataSource,
    private val note: Note?,
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class, InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val noteDetailsViewModel = getViewModel(
            "NoteDetailsScreen",
            viewModelFactory { NoteDetailsViewModel() }
        )
        val state = noteDetailsViewModel.state
        LaunchedEffect(true) {
            note?.let {
                noteDetailsViewModel.state = state.copy(
                    title = note.title,
                    noteContent = note.content,
                    priority = getPropertyFromString(note.priority),
                    noteId = note.id
                )
            }
        }
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(state.navBackScreenNow){
            if (state.navBackScreenNow){
                navigator.pop()
            }
        }

        Scaffold(
            topBar = {
                TaskToolbar(
                    onSaveClick = {
                        noteDetailsViewModel.onEvent(NoteDetailsEvents.OnSaveClick)
                    },
                    onCloseClick = {
                        navigator.pop()
                    },
                    onUpdateClick = {},
                    onBackClick = {
                        navigator.pop()
                    },
                    onDeleteIconClick = {
                        noteDetailsViewModel.onEvent(NoteDetailsEvents.OnDeleteIconClick)
                    },
                    note = note
                )
            },
            content = {
                Box (
                    modifier = Modifier.padding(it).fillMaxSize()
                        ){
                    NoteDetailsView(
                        title = state.title,
                        onTitleChange = { title ->
                            noteDetailsViewModel.onEvent(NoteDetailsEvents.OnTitleTextChange(title))
                        },
                        desiption = state.noteContent,
                        onDescriptionChange = { content ->
                            noteDetailsViewModel.onEvent(NoteDetailsEvents.OnContentTextChange(content))
                        },
                        priority = state.priority,
                        onPrioritySelected = { priority ->
                            noteDetailsViewModel.onEvent(NoteDetailsEvents.OnPriorityChange(priority))

                        },
                        dropMenuItemExpanded = state.isDetailsPriorityMenuShown,
                        onClickDropMenu = {
                            noteDetailsViewModel.onEvent(NoteDetailsEvents.OnClickDropMenu)
                        }
                    )
                    DisplayAlertDialog(
                        showAlert = state.isShowAlertDialog,
                        title = stringResource(SharedRes.strings.delete_x, listOf(note?.title)),
                        text = stringResource(SharedRes.strings.sure_delete, listOf(note?.title)),
                        closeDialog = {
                            noteDetailsViewModel.onEvent(NoteDetailsEvents.OnCloseDialog)
                        },
                        confirmClick = {
                            noteDetailsViewModel.onEvent(NoteDetailsEvents.OnConfirmDeleteClick)
                        }
                    )
                }
            },
        )
    }

}


//fun showToast(context: Context) {
////    Toast.makeText(context, "Empty Fields !", Toast.LENGTH_SHORT).show()
//}
