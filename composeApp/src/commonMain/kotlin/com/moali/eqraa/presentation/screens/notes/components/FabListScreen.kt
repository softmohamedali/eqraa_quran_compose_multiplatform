package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.moali.eqraa.domain.models.Note

@Composable
fun FabListScreen(
    navToTaskScreeen: (Note?) -> Unit
) {
    FloatingActionButton(
        onClick = { navToTaskScreeen(null) },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}