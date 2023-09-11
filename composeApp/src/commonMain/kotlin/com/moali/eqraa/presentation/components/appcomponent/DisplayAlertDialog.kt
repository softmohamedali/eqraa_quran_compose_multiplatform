package com.moali.eqraa.presentation.components.appcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun DisplayAlertDialog(
    showAlert: Boolean,
    title: String,
    text: String,
    negativeClick: () -> Unit,
    positiveClick: () -> Unit,
    positiveActionText: String = stringResource(SharedRes.strings.confirm),
    negativeActionText: String = stringResource(SharedRes.strings.cancel)
) {
    if (showAlert) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = title, style = typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = text, style = typography.body2)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = negativeClick) {
                            Text(text =negativeActionText)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(onClick = {
                            positiveClick()
                            negativeClick()
                        }) {
                            Text(text = positiveActionText)
                        }
                    }
                }
            }
        }
    }
}