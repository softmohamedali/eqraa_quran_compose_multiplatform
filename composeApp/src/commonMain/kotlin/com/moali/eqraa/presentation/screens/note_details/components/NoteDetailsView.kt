package com.moali.eqraa.presentation.screens.note_details.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.presentation.screens.note_details.components.ProrityDropDownItem
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsView(
    title:String,
    onTitleChange:(String)-> Unit,
    priority: Priority,
    onPrioritySelected:(Priority)->Unit,
    desiption:String,
    onDescriptionChange:(String)->Unit,
    modifier: Modifier=Modifier,
    dropMenuItemExpanded:Boolean,
    onClickDropMenu:()->Unit
){
    val scrollableState= rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 8.dp)
            .verticalScroll(scrollableState)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value =title,
            onValueChange ={onTitleChange(it)},
            singleLine = true,
            label = { Text(text = stringResource(SharedRes.strings.title),)}
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProrityDropDownItem(
            priority = priority,
            onItemSelected = {onPrioritySelected(it)},
            modifier = Modifier,
            isExpanded=dropMenuItemExpanded,
            onClick={onClickDropMenu()}
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize().weight(1f),
            value =desiption,
            onValueChange ={onDescriptionChange(it)},
            label = { Text(text = stringResource(SharedRes.strings.your_description_here),)},

        )
    }

}