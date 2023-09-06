package com.moali.eqraa.presentation.screens.note_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.moali.eqraa.core.shared.Dispatchers
import com.moali.eqraa.di.DIManualAppModule
import com.moali.eqraa.domain.abstractions.local.NoteDataSource
import com.moali.eqraa.domain.models.Note
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteDetailsViewModel(
//    private val dispatchers: Dispatchers = DIManualAppModule.disPatchers,
//    private val noteDataSource: NoteDataSource
) : ViewModel(),KoinComponent {

    private val dispatchers :Dispatchers by inject()
    private val noteDataSource: NoteDataSource by inject()
    var state by mutableStateOf(NoteDetailsState())

    fun onEvent(event: NoteDetailsEvents) {
        when (event) {
            is NoteDetailsEvents.OnTitleTextChange -> {
                state = state.copy(title = event.text)
                Logger.i { "titile change -> ${state.title}" }
            }

            is NoteDetailsEvents.OnContentTextChange -> {
                state = state.copy(noteContent = event.text)
            }

            is NoteDetailsEvents.OnPriorityChange -> {
                state = state.copy(
                    priority = event.priority,
                    isDetailsPriorityMenuShown = false
                )
            }

            is NoteDetailsEvents.OnClickDropMenu -> {
                state = state.copy(isDetailsPriorityMenuShown = !state.isDetailsPriorityMenuShown)
            }

            is NoteDetailsEvents.OnSaveClick -> {
                Logger.i { "NoteDetailsEvents.OnSaveClick----------" +""}
                addNote()
                state = state.copy(
                    navBackScreenNow = true
                )
            }

            is NoteDetailsEvents.OnUpdateClick -> {

            }

            is NoteDetailsEvents.OnCloseClick -> {

            }

            is NoteDetailsEvents.OnConfirmDeleteClick -> {
                deleteNoteDB()
                state = state.copy(
                    isShowAlertDialog = false,
                    navBackScreenNow = true
                )
            }

            NoteDetailsEvents.OnCloseDialog -> {
                state = state.copy(
                    isShowAlertDialog = false,
                )
            }
            NoteDetailsEvents.OnDeleteIconClick -> {
                state = state.copy(
                    isShowAlertDialog = true,
                )
            }
        }
    }

    private fun addNote() {
        viewModelScope.launch(dispatchers.main) {
            val note = Note(
                id=null,
                title = state.title,
                priority = state.priority.name,
                content = state.noteContent
            )
            noteDataSource.insertNote(note = note)
        }
    }

    fun deleteNoteDB() {
        viewModelScope.launch(dispatchers.io) {
            noteDataSource.deleteNote(id = state.noteId!!)
        }
    }

    fun validateFields(): Boolean {
        if (state.title.isEmpty() && state.noteContent.isEmpty()) {
            return false
        }
        return true
    }
}