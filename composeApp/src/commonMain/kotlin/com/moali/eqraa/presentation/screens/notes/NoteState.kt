package com.moali.eqraa.presentation.screens.notes

import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.ToolBarState

data class NoteState(
    val loading:Boolean=false,
    val error:String?="",
    val success:Boolean=false,
    val priority: Priority = Priority.LOW,
    val isPriorityMenuShown:Boolean=false,
    val isActionMenuShown:Boolean=false,
    val isShowDeleteDialog:Boolean=false,
    val action: Action=Action.NONE,
    val toolbarSearchValue:String="",
    val allNotes:List<Note> = emptyList(),
    val searchNotes:List<Note> = emptyList(),
    val noteToolBarState:ToolBarState =ToolBarState.CLOSE
)

