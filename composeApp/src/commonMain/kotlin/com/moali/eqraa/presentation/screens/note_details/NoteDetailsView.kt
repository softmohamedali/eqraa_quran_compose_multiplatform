package com.moali.eqraa.presentation.screens.note_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Priority


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsView(
    title:String,
    onTitleChange:(String)-> Unit,
    priority: Priority,
    onPrioritySelected:(Priority)->Unit,
    desiption:String,
    onDescriptionChange:(String)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value =title,
            onValueChange ={onTitleChange(it)},
            singleLine = true,
            label = { Text(text = "title")}
        )
        Spacer(modifier = Modifier.height(12.dp))
//        ProrityDropDownItem(
//            priority = priority,
//            OnItemSelected = {onPrioritySelected(it)}
//        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value =desiption,
            onValueChange ={onDescriptionChange(it)},
            label = { Text(text = "your discription here")},

        )
    }

}