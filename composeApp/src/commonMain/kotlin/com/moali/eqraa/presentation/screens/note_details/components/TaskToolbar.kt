package com.moali.eqraa.presentation.screens.note_details.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.moali.eqraa.domain.models.Note
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TaskToolbar(
    note: Note?,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onBackClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
) {
    if (note == null)
        AddTaskToolbar(
            onSaveClick = onSaveClick,
            onBackClick = onBackClick
        )
    else
        EditTaskToolbar(
            note = note,
            onCloseClick = onCloseClick,
            onUpdateClick = onUpdateClick,
            onDeleteIconClick = onDeleteIconClick,
        )
}

@Composable
fun AddTaskToolbar(
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        title = {
            Text(text = stringResource(SharedRes.strings.add_note), color = MaterialTheme.colorScheme.onPrimary)
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            Button(onClick = { onSaveClick() }) {
                Text(
                    stringResource(SharedRes.strings.save),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun EditTaskToolbar(
    note: Note,
    onCloseClick: () -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteIconClick: () -> Unit,
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colorScheme.primary,
        title = {
            Text(
                text = note.title,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { onCloseClick() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            ExsistingAction(
                onUpdateClick = onUpdateClick,
                onDeleteIconClick = onDeleteIconClick,
            )
        }
    )
}

@Composable
fun ExsistingAction(
    onDeleteIconClick: () -> Unit,
    onUpdateClick: () -> Unit,
) {


//    IconButton(onClick = { onUpdateClick() }) {
//        Icon(
//            imageVector = Icons.Filled.Edit,
//            contentDescription = "",
//            tint = MaterialTheme.colorScheme.onPrimary
//        )
//    }
    IconButton(onClick = { onDeleteIconClick() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}


