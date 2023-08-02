package com.moali.eqraa.domain.abstractions

import com.moali.eqraa.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(id:Long)
    fun getNotes():Flow<List<Note>>
    fun getNotesHighPriority():Flow<List<Note>>
    fun getNotesLowPriority():Flow<List<Note>>
    fun getNotesMedPriority():Flow<List<Note>>
    fun searchNoteByTitle(title:String):Flow<List<Note>>
}