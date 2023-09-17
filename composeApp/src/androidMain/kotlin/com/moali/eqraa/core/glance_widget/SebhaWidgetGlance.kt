package com.moali.eqraa.core.glance_widget

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.ButtonDefaults
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.moali.eqraa.domain.abstractions.local.DataStoreOper
import com.moali.eqraa.ui.theme.md_theme_light_primary
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SebhaWidgetGlance : GlanceAppWidget(),KoinComponent {

    val pref:DataStoreOper by inject()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val countvalaer = pref.getSebhaPrefAsFlow()
        provideContent {
            val counter by countvalaer.collectAsState(0)
            MyContent(counter.toString())
        }
    }


    @Composable
    private fun MyContent(
        counter:String
    ) {

        Column(
            modifier = GlanceModifier.width(140.dp).height(180.dp).padding(12.dp)
                .cornerRadius(80.dp)
                .background(md_theme_light_primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = GlanceModifier
                    .fillMaxWidth(),
                text = "Sebha",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = ColorProvider(MaterialTheme.colorScheme.surface)
                ),
            )
            Spacer(modifier = GlanceModifier.height(10.dp))
            Text(
                modifier = GlanceModifier.padding(8.dp)
                    .cornerRadius(40.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = counter,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = ColorProvider(Color.White)
                ),
            )
            Spacer(modifier = GlanceModifier.height(20.dp))
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        text = "",
                        modifier = GlanceModifier.fillMaxWidth().fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(
                            contentColor =ColorProvider( Color.White),
                            backgroundColor = ColorProvider( Color.White),
                        ) ,
                        onClick = actionRunCallback(
                            IncrementActionCallback(

                        )::class.java),
                        )
                }

            }
        }
    }


}

class SimpleCounterWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = SebhaWidgetGlance
}

class IncrementActionCallback : ActionCallback ,KoinComponent{
    val pref:DataStoreOper by inject()
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) {
            if (pref.getSebhaCounter()!=null){
                pref.saveSebhaCounter(pref.getSebhaCounter()!!+1)
            }else{
                pref.saveSebhaCounter(1)
            }
        }
        SebhaWidgetGlance.update(context, glanceId)
    }
}


































//                Row(
//                    modifier = GlanceModifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Spacer(modifier = GlanceModifier.defaultWeight())
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Text(
//                            text = "BackOne",
//                            style = TextStyle(
//                                color = ColorProvider(MaterialTheme.colorScheme.surface)
//                            ),
//                        )
//                        Button(
//                            text = "",
//                            modifier = GlanceModifier.size(30.dp)
//                                .background(ColorProvider(MaterialTheme.colorScheme.surface)),
//                            onClick = actionRunCallback(IncrementActionCallback::class.java),
//                        )
//                    }
//                    Spacer(modifier = GlanceModifier.defaultWeight())
//                    Spacer(modifier = GlanceModifier.defaultWeight())
////                    Column(
////                        horizontalAlignment = Alignment.CenterHorizontally
////                    ) {
////                        Text(
////                            text = "Restart",
////                            style = TextStyle(
////                                color = ColorProvider(MaterialTheme.colorScheme.surface)
////                            ),
////                        )
////                        Button(
////                            text = "",
////                            modifier = GlanceModifier.size(30.dp)
////                                .background(ColorProvider(MaterialTheme.colorScheme.surface)),
////                            onClick = actionRunCallback(IncrementActionCallback::class.java),
////                        )
////                    }
////                    Spacer(modifier = GlanceModifier.defaultWeight())
//                }


