package com.moali.eqraa.presentation.screens.sebha

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.action
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.moali.eqraa.core.utils.DataStorePhrefrencesKeys
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SebhaWidget : GlanceAppWidget(),KoinComponent {


    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            val count = currentState(key = DataStorePhrefrencesKeys.sebhaPrefKey) ?: 0
            MyContent(count.toString())
        }
    }


    @Composable
    private fun MyContent(
        counter:String
    ) {

        Column(
            modifier = GlanceModifier.padding(10.dp).width(200.dp).height(280.dp).padding(12.dp)
                .cornerRadius(80.dp)
                .background(ColorProvider(MaterialTheme.colorScheme.secondary)),
            verticalAlignment = Alignment.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = GlanceModifier.padding(12.dp)
                    .cornerRadius(40.dp)
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = counter,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = ColorProvider(MaterialTheme.colorScheme.surface)
                ),
            )
            Column(
                modifier = GlanceModifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = GlanceModifier.defaultWeight())
                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = GlanceModifier.defaultWeight())
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "BackOne",
                            style = TextStyle(
                                color = ColorProvider(MaterialTheme.colorScheme.surface)
                            ),
                        )
                        Button(
                            text = "",
                            modifier = GlanceModifier.size(30.dp)
                                .background(ColorProvider(MaterialTheme.colorScheme.surface)),
                            onClick = actionRunCallback(IncrementActionCallback::class.java),
                        )
                    }
                    Spacer(modifier = GlanceModifier.defaultWeight())
                    Spacer(modifier = GlanceModifier.defaultWeight())
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Restart",
                            style = TextStyle(
                                color = ColorProvider(MaterialTheme.colorScheme.surface)
                            ),
                        )
                        Button(
                            text = "",
                            modifier = GlanceModifier.size(30.dp)
                                .background(ColorProvider(MaterialTheme.colorScheme.surface)),
                            onClick = actionRunCallback(IncrementActionCallback::class.java),
                        )
                    }
                    Spacer(modifier = GlanceModifier.defaultWeight())
                }

                Spacer(modifier = GlanceModifier.defaultWeight())
                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        text = "",
                        modifier = GlanceModifier.size(80.dp)
                            .background(ColorProvider(MaterialTheme.colorScheme.surface)),
                        onClick = actionRunCallback(IncrementActionCallback::class.java),
                        )
                }

            }
        }
    }
}

class SimpleCounterWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = SebhaWidget
}

class IncrementActionCallback : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, glanceId) { prefs ->
            val currentCount = prefs[DataStorePhrefrencesKeys.sebhaPrefKey]
            if (currentCount != null) {
                prefs[DataStorePhrefrencesKeys.sebhaPrefKey] = currentCount + 1

            } else {
                prefs[DataStorePhrefrencesKeys.sebhaPrefKey] = 1
            }
        }
        SebhaWidget.update(context, glanceId)
    }
}