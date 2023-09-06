package com.moali.eqraa

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.moali.eqraa.core.shared.utils.EqraaTheme
import com.moali.eqraa.presentation.screens.home.HomeScreen

@Composable
fun MyApp(
    darkTheme: Boolean,
    dynamicColor: Boolean,
//    noteDataSource: NoteDataSource?
) {
    EqraaTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
//        val viewModel = getViewModel(
//            key = "contact-list-screen",
//            factory = viewModelFactory {
//                ContactListViewModel(appModule.contactDataSource)
//            }
//        )
//        val state by viewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigator(
                HomeScreen(),
            )
        }
    }
}