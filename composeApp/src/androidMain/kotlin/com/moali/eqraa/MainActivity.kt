package com.moali.eqraa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.lifecycleScope
import com.moali.eqraa.MyApp
import com.moali.eqraa.core.utils.MainCompnentAction
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.scope
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent.inject


class MainActivity : ComponentActivity() {

    private val pref: DataStoreOper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
            )

        }
        startListenActions()
    }

    private fun startListenActions() {
        lifecycleScope.launch {
            pref.listenMainComponentAction().collect{
                if (it==MainCompnentAction.SHARE_APP){
                    shareApp()
                    pref.sendAction(null)
                }
            }
        }

    }


    fun shareApp() {
        super.startActivity(intent)
        val intent= Intent()
        intent.action=Intent.ACTION_SEND
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey Check out this Great app: \n" +
                    "https://play.google.com/store/apps/details?id=com.moali.eqraa.androidApp&pli=1" +
                    "and you can support the application developer on \n" +
                    "https://www.buymeacoffee.com/softmohamem"
        )
        intent.type="text/plain"
        startActivity(Intent.createChooser(intent,"Share To:"))
    }



}


