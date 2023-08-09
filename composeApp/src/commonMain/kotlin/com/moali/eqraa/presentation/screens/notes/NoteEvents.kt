package com.moali.eqraa.presentation.screens.notes

import com.moali.eqraa.domain.models.Priority

sealed class NoteEvents {
    object OnHandleDBAction : NoteEvents()
    object OnUndoDeleteTask : NoteEvents()
    class OnPriorityClickSaveState(val priority: Priority) : NoteEvents()
    object OnActionMenuClick : NoteEvents()
    object OnDeleteAllClick : NoteEvents()
    object OnConfirmDialogClick : NoteEvents()
    object OnSearchOpenClick : NoteEvents()
    object OnFilterClick : NoteEvents()
    object OnSearchClick : NoteEvents()
    object OnCloseClick : NoteEvents()

    object OnCloseAlertDelete : NoteEvents()

    class OnSearchTextChange(val text: String) : NoteEvents()

}


