package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.presentation.components.appcomponent.PriorityItem
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun OthersMenu(
    onDeleteClick:()->Unit,
    modifier: Modifier = Modifier
) {
    Card (
        modifier =modifier.padding(4.dp)
            .background(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Column {
            Text(
                text = stringResource(SharedRes.strings.delete_all),
                modifier = Modifier.padding(all = 10.dp)
                    .clickable {
                        onDeleteClick()
                    }
            )
        }
    }


}