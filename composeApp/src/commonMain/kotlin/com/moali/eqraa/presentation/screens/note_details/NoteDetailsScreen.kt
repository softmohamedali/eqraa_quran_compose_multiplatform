package com.moali.eqraa.presentation.screens.note_details

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.presentation.screens.notes.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    navigateToListScreen: (Action) -> Unit,
    note: Note?,
    noteViewModel: NoteViewModel
) {
//    var title by sharedViewModel.title
//    var description by sharedViewModel.description
//    var priority by sharedViewModel.priority

    Scaffold(
        topBar = {
//            TaskToolbar(
//                {
//                    if (it == Action.NONE) {
//                        navigateToListScreen(it)
//                    } else {
//                        if (sharedViewModel.validateFields()) {
//                            navigateToListScreen(it)
//                        } else {
//                            showToast(context)
//                        }
//                    }
//                },
//                task
//            )
        },
        content = {
            NoteDetailsView(
                title = note?.title?:"",
                onTitleChange = { },
                desiption = "description",
                onDescriptionChange = { },
                priority = Priority.NONE,
                onPrioritySelected = {
//                    priority = it
                }
            )
        },
    )
}

//fun showToast(context: Context) {
////    Toast.makeText(context, "Empty Fields !", Toast.LENGTH_SHORT).show()
//}
