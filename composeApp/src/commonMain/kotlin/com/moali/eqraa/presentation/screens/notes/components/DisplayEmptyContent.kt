package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun DisplayEmptyContent(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = "sad face",
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(SharedRes.strings.no_data_found_error_X, listOf("")),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp
        )
    }
}