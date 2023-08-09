package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.presentation.components.appcomponent.PriorityItem


@Composable
fun PriorityMenu(
    onClick:(Priority)->Unit,
    modifier: Modifier=Modifier,
    showNone:Boolean=false
) {
    Card (
        modifier =modifier.padding(4.dp)
        .background(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(4.dp),
    ){
        Column {
            PriorityItem(
                priority = Priority.HIGH,
                onClick = {
                    onClick(it)
                }
            )
            PriorityItem(
                priority = Priority.MEDIUM,
                onClick = {
                    onClick(it)
                }
            )
            PriorityItem(
                priority = Priority.LOW,
                onClick = {
                    onClick(it)
                }
            )
            if (showNone){
                PriorityItem(
                    priority = Priority.NONE,
                    onClick = {
                        onClick(it)
                    }
                )
            }
        }
    }
}