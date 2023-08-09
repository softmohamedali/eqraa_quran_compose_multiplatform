package com.moali.eqraa.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.ui.resources.ContentDescriptions
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HomeTopAppBar(
    modifier: Modifier=Modifier,
    infoClick:()->Unit={},
    shareClick:()->Unit={},
    rateClick:()->Unit={},
    menuClick:()->Unit={},
){
    Row (
    modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primary)
    ){
        Row(
            modifier = Modifier.fillMaxHeight().weight(5f)
        ) {
            IconButton(
                modifier = Modifier.padding(3.dp),
                onClick = {infoClick()},
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = ContentDescriptions.INFO,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
            IconButton(
                modifier = Modifier.padding(3.dp),
                onClick = {shareClick()},
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        contentDescription = ContentDescriptions.INFO,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
            IconButton(
                modifier = Modifier.padding(3.dp),
                onClick = {rateClick()},
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = ContentDescriptions.INFO,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
        Row (
            modifier = Modifier.fillMaxHeight().weight(5f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(SharedRes.strings.app_name),
                color = MaterialTheme.colorScheme.onPrimary
            )
            IconButton(
                onClick = {menuClick()},
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = ContentDescriptions.INFO,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    }
}