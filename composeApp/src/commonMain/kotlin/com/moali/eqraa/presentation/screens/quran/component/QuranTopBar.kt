package com.moali.eqraa.presentation.screens.quran.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranTopAppbar(
    title: String,
    onBackClick: () -> Unit = {},
    isBack: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    searchQuery:String,
    onSearchQueryChange:(String)->Unit,
    onCloseClick:()->Unit,
    onSearchClick:()->Unit,
    isSearching:Boolean,
) {


    TopAppBar(
        title = {
            if (isSearching) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    value = searchQuery,
                    onValueChange =  onSearchQueryChange,
                    placeholder = { Text(text = "Search...", color = Color.White) },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                onCloseClick()
                            }
                        )
                    },
                    textStyle = TextStyle(color = Color.White),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        cursorColor = Color.White,
                        textColor = Color.White
                    )
                )
            } else {
                Text(
                    text = title,
                    modifier = Modifier,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            if (!isSearching) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { onSearchClick() },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            actions()
        },
        navigationIcon = {
            if (isBack) {
                Icon(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { onBackClick() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    )
}