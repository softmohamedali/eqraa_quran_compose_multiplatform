package com.moali.eqraa.presentation.screens.note_details

import com.moali.eqraa.domain.models.Priority

sealed class NoteDetailsEvents {

    class OnTitleTextChange(val text: String) : NoteDetailsEvents()
    class OnContentTextChange(val text: String) : NoteDetailsEvents()

    class OnPriorityChange(val priority: Priority) : NoteDetailsEvents()

    object OnClickDropMenu : NoteDetailsEvents()

    object OnSaveClick : NoteDetailsEvents()

    object OnUpdateClick : NoteDetailsEvents()

    object OnCloseClick : NoteDetailsEvents()
    object OnConfirmDeleteClick : NoteDetailsEvents()

    object OnDeleteIconClick : NoteDetailsEvents()
    object OnCloseDialog : NoteDetailsEvents()


}