package com.moali.eqraa.data.local

import com.moali.eqraa.database.EqraaDatabase
import com.moali.eqraa.domain.abstractions.NoteDataSource
import com.moali.eqraa.domain.models.Note
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightNoteDataSource(
    private val db: EqraaDatabase
) : NoteDataSource {

    private val querey = db.noteQueries

    override suspend fun insertNote(note: Note) {
        querey.insertNote(
            id = note.id.toLong(),
            title = note.title,
            content = note.content,
            createAt = Clock.System.now().toEpochMilliseconds(),
            priority = note.priority.toString(),
            rememberMe = note.rememberMe,
            image = note.image
        )
    }

    override suspend fun deleteNote(id: Long) {
        querey.deleteNote(id=id)
    }

    override fun getNotes(): Flow<List<Note>> {
        return querey.getNotes().asFlow()
            .mapToList()
            .map {
                it.toListNote()
            }
    }

    override fun getNotesHighPriority(): Flow<List<Note>> {
        return querey.getNotesHighPriority().asFlow()
            .mapToList()
            .map {
                it.toListNote()
            }
    }

    override fun getNotesLowPriority(): Flow<List<Note>> {
        return querey.getNotesLowPriority().asFlow()
            .mapToList()
            .map {
                it.toListNote()
            }
    }

    override fun getNotesMedPriority(): Flow<List<Note>> {
        return querey.getNotesMedPriority().asFlow()
            .mapToList()
            .map {
                it.toListNote()
            }
    }

    override fun searchNoteByTitle(title:String): Flow<List<Note>> {
        return querey.searchNoteByTitle(title).asFlow()
            .mapToList()
            .map {
                it.toListNote()
            }
    }

}