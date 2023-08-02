import com.moali.eqraa.MyApp
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Eqraa") {

        }
    }
}
