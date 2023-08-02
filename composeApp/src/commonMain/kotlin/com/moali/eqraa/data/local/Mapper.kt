package com.moali.eqraa.data.local

import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import database.NoteEntity

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id.toInt(),
        title = this.title,
        content = this.content,
        createAt = this.createAt.toInt(),
        priority = Priority.NONE,
        //ToDo
        rememberMe = this.rememberMe,
        image = this.image,
    )
}

fun List<NoteEntity>.toListNote():List<Note>{
    return this.map { it.toNote() }
}