package com.moali.eqraa.presentation.screens.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import com.moali.eqraa.core.shared.Dispatchers
import com.moali.eqraa.di.DIManualAppModule
import com.moali.eqraa.domain.abstractions.NoteDataSource
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.PriorityEntity
import com.moali.eqraa.domain.models.ToolBarState
import com.moali.eqraa.domain.models.getPropertyFromString
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dispatchers: Dispatchers = DIManualAppModule.disPatchers,
    private val noteDataSource: NoteDataSource
) : ViewModel() {

    var state by mutableStateOf(NoteState())

    init {
        getState()
    }
    fun onEvent(event: NoteEvents){
        when(event){
            is NoteEvents.OnHandleDBAction->{
                handleDBAction(state.action)
            }
            is  NoteEvents.OnUndoDeleteTask->{
                state=state.copy(action = Action.UNDO)
            }
            is NoteEvents.OnPriorityClickSaveState->{
                saveState(event.priority)
                state=state.copy(isPriorityMenuShown = false)
            }
            is NoteEvents.OnActionMenuClick->{
                state=state.copy(
                    isActionMenuShown = !state.isActionMenuShown,
                    isPriorityMenuShown = false
                )
            }
            is NoteEvents.OnDeleteAllClick->{
                state=state.copy(
                    isActionMenuShown = false,
                    isShowDeleteDialog = true
                )
            }
            is NoteEvents.OnConfirmDialogClick->{
                handleDBAction(Action.DELETE_ALL,)
                state=state.copy(
                    action = Action.DELETE_ALL,
                    isShowDeleteDialog = false,
                )
            }
            is NoteEvents.OnSearchOpenClick->{
                state=state.copy(
                    noteToolBarState = ToolBarState.OPEN,
                    isActionMenuShown = false,
                )
            }
            is NoteEvents.OnFilterClick->{
                state=state.copy(
                    isPriorityMenuShown = !state.isPriorityMenuShown,
                    isActionMenuShown = false,
                )
            }
            is NoteEvents.OnSearchClick->{
                getSearchNote(state.toolbarSearchValue)
            }
            is NoteEvents.OnCloseClick->{
                state=state.copy(
                    noteToolBarState = ToolBarState.CLOSE,
                    toolbarSearchValue=""
                )
            }
            is NoteEvents.OnSearchTextChange->{
                state=state.copy(toolbarSearchValue=event.text)

            }

            NoteEvents.OnCloseAlertDelete ->{
                state=state.copy(isShowDeleteDialog = false)
            }
        }
    }


    private fun getAllNote() {
        state = state.copy(loading = true, allNotes = emptyList())
        try {
            viewModelScope.launch {
                when(state.priority){
                    Priority.NONE ->{
                        noteDataSource.getNotes().collect {
                            state = state.copy(
                                allNotes = it,
                                success = true,
                                loading = false
                            )
                            Logger.i { it.toString() }
                        }
                    }
                    Priority.LOW ->{
                        noteDataSource.getNotesLowPriority().collect {
                            state = state.copy(
                                allNotes = it,
                                success = true,
                                loading = false
                            )
                        }
                    }
                    Priority.HIGH ->{
                        noteDataSource.getNotesHighPriority().collect {
                            state = state.copy(
                                allNotes = it,
                                success = true,
                                loading = false
                            )
                        }
                    }
                    Priority.MEDIUM ->{
                        noteDataSource.getNotesMedPriority().collect {
                            state = state.copy(
                                allNotes = it,
                                success = true,
                                loading = false
                            )
                            Logger.i { it.toString() }
                        }
                    }
                }
            }
        } catch (e: Throwable) {
            state = state.copy(
                success = false,
                loading = false,
                error = e.message!!
            )
        }
    }

    private fun deleteAllNote() {
        viewModelScope.launch(dispatchers.io) {
            noteDataSource.deleteAllNote()
        }
    }

    private fun getSearchNote(query: String) {
        state = state.copy(
            loading = true,
            isActionMenuShown = false,
        )
        try {
            viewModelScope.launch(dispatchers.main) {
                noteDataSource.searchNoteByTitle(query).collect {
                    state = state.copy(
                        searchNotes = it,
                        success = true,
                        loading = false,
                        noteToolBarState = ToolBarState.TRIGER
                    )
                }
            }
        } catch (e: Throwable) {
            state = state.copy(
                success = false,
                loading = false,
                error = e.message!!,
                noteToolBarState = ToolBarState.TRIGER
            )
        }
    }

    private fun saveState(priority: Priority) {
        viewModelScope.launch(dispatchers.io) {
            noteDataSource.saveStatePriority(priority = PriorityEntity(type = priority.name))
        }
    }


    private fun getState() {
        state = state.copy(loading = true)
        try {
            viewModelScope.launch(dispatchers.main) {
                noteDataSource.getStatePriority().collect {
                    val priorityList = it.map { getPropertyFromString(it.type) }
                    if (priorityList.isNotEmpty()) {
                        state = state.copy(
                            priority = priorityList[0],
                            success = true,
                            loading = false,
                        )
                        getAllNote()
                    }

                }
            }
        } catch (e: Throwable) {
            state = state.copy(
                success = false,
                loading = false,
                error = e.message!!,
            )
        }
    }



    fun handleDBAction(action: Action) {

        when (action) {
            Action.ADD -> {

            }
            Action.DELETE -> {

            }
            Action.UNDO -> {

            }
            Action.UPDATE -> {}
            Action.NONE -> {}
            Action.DELETE_ALL -> {
                deleteAllNote()
            }
        }
        state = state.copy(action = Action.NONE)

    }


    override fun onCleared() {
        super.onCleared()
        saveState(Priority.NONE)
    }
}