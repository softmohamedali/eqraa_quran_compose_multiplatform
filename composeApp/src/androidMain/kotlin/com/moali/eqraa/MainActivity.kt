package com.moali.eqraa

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.moali.eqraa.core.utils.MainCompnentAction
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {

    private val pref: DataStoreOper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this) {}
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().build()
        )
        startListenActions()
        setContent {
            MyApp(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
            )

        }

    }

    private fun startListenActions() {
        lifecycleScope.launch {
            pref.listenMainComponentAction().collect{
                when(it){
                    MainCompnentAction.SHARE_APP ->{
                        shareMassage(
                            "Hey Check out this Great app: \n" +
                                    "https://play.google.com/store/apps/details?id=com.moali.eqraa.androidApp&pli=1" +
                                    "and you can support the application developer on \n" +
                                    "https://www.buymeacoffee.com/softmohamem"
                        )
                        pref.sendAction(null)
                    }
                    MainCompnentAction.SHARE_SUPPORT ->{
                        shareMassage(
                            "Hey Check out this Great app: \n" +
                                    "https://play.google.com/store/apps/details?id=com.moali.eqraa.androidApp&pli=1" +
                                    "and you can support the application developer on \n" +
                                    "https://www.buymeacoffee.com/softmohamem"
                        )
                        pref.sendAction(null)
                    }
                    MainCompnentAction.OPEN_SUPPORT_LINK ->{
                        openUrl("https://www.buymeacoffee.com/softmohamem")
                        pref.sendAction(null)
                    }
                }

            }
        }

    }


    private fun shareMassage(link:String) {
        super.startActivity(intent)
        val intent= Intent()
        intent.action=Intent.ACTION_SEND
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(
            Intent.EXTRA_TEXT,
            link
        )
        intent.type="text/plain"
        startActivity(Intent.createChooser(intent,"Share To:"))
    }

    private fun openUrl(url: String) {
        val webPage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No web browser app found", Toast.LENGTH_SHORT).show()
        }
    }




}


