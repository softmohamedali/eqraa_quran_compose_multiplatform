package com.moali.eqraa.presentation.screens.notes.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.ToolBarState
import com.moali.eqraa.presentation.components.appcomponent.DisplayAlertDialog
import com.moali.eqraa.presentation.screens.notes.NoteViewModel
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ToolBarListScreen(
    toolBarState: ToolBarState,
    toolBarSearchValue: String,
    onConfirmDialogClick: () -> Unit,
    isShowAlertDialog:Boolean,
    onCloseDialog:()->Unit,
    onSearchOpenClick: () -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    onCloseClick: () -> Unit,
    onActionMenuClick:()->Unit,
    onSearchTextChange: (String) -> Unit

) {



    DisplayAlertDialog(
        showAlert = isShowAlertDialog,
        title = stringResource(SharedRes.strings.delete_all)+" ?",
        text = stringResource(SharedRes.strings.sure_delete_all),
        closeDialog = { onCloseDialog() },
        confirmClick = {
            onConfirmDialogClick()
        }
    )
    when (toolBarState) {
        ToolBarState.CLOSE -> {
            DefaultToolBarListScreen(
                searchClick = {
                    onSearchOpenClick()
                },
                filterClick = {
                    onFilterClick()
                },
                onActionClick = {
                    onActionMenuClick()
                }
            )
        }

        else -> {
            SearchToolBar(
                text = toolBarSearchValue,
                onCloseClik = {
                    onCloseClick()
                },
                onSearchClick = {
                    onSearchClick()
                },
                onTextChanged = {
                    //TODO onTextChanged
//                    noteViewModel.toolbarSearchValue.value = it
                    onSearchTextChange(it)
                }
            )
        }
    }
}

@Composable
fun HandleDeleteClick(
    noteViewModel: NoteViewModel
) {


}

@Composable
fun DefaultToolBarListScreen(
    searchClick: () -> Unit,
    filterClick: () -> Unit,
    onActionClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(SharedRes.strings.notes),
            color = MaterialTheme.colorScheme.onPrimary)
                },
        actions = {
            ToolBarAction(
                searchClick = searchClick,
                filterClick = filterClick,
                deleteClick = onActionClick
            )
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun ToolBarAction(
    searchClick: () -> Unit,
    filterClick: () -> Unit,
    deleteClick: () -> Unit,
) {
    SearchAction(searchClick = searchClick)
    FilterAction(filterClick = filterClick)
    MenuAction(onClick = deleteClick)

}

@Composable
fun SearchAction(
    searchClick: () -> Unit
) {
    IconButton(onClick = {
        searchClick()
    }) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun FilterAction(
    filterClick: () -> Unit
) {
    IconButton(onClick = {
        filterClick()
    }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Composable
fun MenuAction(
    onClick: () -> Unit
) {

    IconButton(onClick = {
        onClick()
    }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onPrimary
        )

    }
}


@Composable
fun SearchToolBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClik: () -> Unit,
    onSearchClick: (String) -> Unit
) {
    var isEmpty by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            leadingIcon = {
                IconButton(onClick = {
                    onSearchClick(text)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            placeholder = { Text(text = stringResource(SharedRes.strings.search)) },
            trailingIcon = {
                IconButton(onClick = {
                    if (isEmpty) {
                        onTextChanged("")
                        isEmpty = false
                    } else {
                        if (text.isNotEmpty()) {
                            onTextChanged("")
                        } else {
                            onCloseClik()
                            isEmpty = true
                        }
                    }

                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClick(text)
            }),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                disabledIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = MaterialTheme.colorScheme.primary,
                placeholderColor =  MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
            )
        )
    }

}







