package com.moali.eqraa.data.local

import co.touchlab.kermit.Logger
import com.moali.eqraa.database.EqraaDatabase
import com.moali.eqraa.domain.abstractions.NoteDataSource
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.PriorityEntity
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.datetime.Clock

class SqlDNoteDataSource(
    private val db: EqraaDatabase
) : NoteDataSource {

    private val querey = db.noteQueries

    override suspend fun insertNote(note: Note) {
        Logger.i { "insertNote() => ${note}" }
        querey.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            createAt = Clock.System.now().toEpochMilliseconds(),
            priority = note.priority,
            rememberMe = note.rememberMe,
            image = note.image
        )
    }

    override suspend fun deleteNote(id: Long) {
        querey.deleteNote(id=id)
    }

    override suspend fun saveStatePriority(priority: PriorityEntity) {
        querey.insertPriority(
            id = priority.id.toLong(),
            type = priority.type
        )
    }

    override suspend fun getStatePriority(): Flow<List<PriorityEntity>> {
        return querey.getStatePriorty().asFlow()
            .mapToList()
            .map {
                it.map { pro-> PriorityEntity(pro.id.toInt(),pro.type) }
            }
    }

    override suspend fun deleteAllNote() {
        querey.deleteAllNote()
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