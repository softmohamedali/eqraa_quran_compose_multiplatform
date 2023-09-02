import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.moali.eqraa.MyApp
import com.moali.eqraa.di.initKoin

fun main() = application {

    initKoin()

    Window(
        title = "Eqraa",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        MyApp(
            darkTheme = false,
            dynamicColor = false,
        )
    }
}