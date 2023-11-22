package com.moali.eqraa

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.moali.eqraa.core.shared.utils.EqraaTheme
import com.moali.eqraa.presentation.screens.home.HomeScreen
import com.moali.eqraa.ui.resources.DrawableResources


private val localDrawableResources = staticCompositionLocalOf { DrawableResources() }
@Composable
fun MyApp(
    darkTheme: Boolean,
    dynamicColor: Boolean,
//    noteDataSource: NoteDataSource?
) {

    val drawableResources = if (darkTheme) DrawableResources() else DrawableResources()

    CompositionLocalProvider(
        localDrawableResources provides drawableResources,
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

}


object Resources {
    val images: DrawableResources
        @Composable
        @ReadOnlyComposable
        get() = localDrawableResources.current

}