package com.moali.eqraa.presentation.screens.note_details

import com.moali.eqraa.domain.models.Priority

data class NoteDetailsState (
    val loading:Boolean=false,
    val error:String?="",
    val success:Boolean=false,
    val title:String="",
    val noteContent:String="",
    val priority: Priority = Priority.LOW,
    val noteId:Long?=null,
    val isDetailsPriorityMenuShown:Boolean=false,
    val isShowAlertDialog:Boolean=false,
    val navBackScreenNow:Boolean=false
){
}