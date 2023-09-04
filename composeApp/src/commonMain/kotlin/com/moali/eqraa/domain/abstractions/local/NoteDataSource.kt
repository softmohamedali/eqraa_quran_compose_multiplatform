package com.moali.eqraa.domain.abstractions.local

import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.PriorityEntity
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(id:Long)
    suspend fun saveStatePriority(priority: PriorityEntity)

    suspend fun getStatePriority( ):Flow<List<PriorityEntity>>
    suspend fun deleteAllNote()
    fun getNotes():Flow<List<Note>>
    fun getNotesHighPriority():Flow<List<Note>>
    fun getNotesLowPriority():Flow<List<Note>>
    fun getNotesMedPriority():Flow<List<Note>>
    fun searchNoteByTitle(title:String):Flow<List<Note>>
}