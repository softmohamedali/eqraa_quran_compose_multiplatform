package com.moali.eqraa.presentation.screens.notes

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.moali.eqraa.core.utils.ResultState
import com.moali.eqraa.domain.models.Action
import com.moali.eqraa.domain.models.Note
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.domain.models.ToolBarState
import com.moali.eqraa.presentation.components.appcomponent.NoteItem
import com.moali.eqraa.presentation.screens.quran.QuranViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.launch


class NoteScreen : Screen {

    @Composable
    override fun Content() {
        //    val tasks by noteViewModel.allTask.collectAsState()
//    val searchTasks by noteViewModel.searshTask.collectAsState()
//    LaunchedEffect(key1 = true) {
//        noteViewModel.getAllTask()
//        noteViewModel.getState()
//    }
        val scaffoldState = rememberScaffoldState()
//    val toolBarSearchValue by noteViewModel.toolbarSearchValue
//    val toolBarState by noteViewModel.toolBarState
//    val state by noteViewModel.stste.collectAsState()
//    val highPriorityTasks by noteViewModel.highPriorityTasks.collectAsState()
//    val lowPriorityTasks by noteViewModel.lowPriorityTasks.collectAsState()
//    val action by noteViewModel.action
        val noteViewModel= getViewModel("QuranScreen", viewModelFactory { NoteViewModel() })

        NoteView(
            navToTaskScreeen ={

            },
            noteViewModel =noteViewModel,
            scaffoldState =scaffoldState,
        )

    }


}



