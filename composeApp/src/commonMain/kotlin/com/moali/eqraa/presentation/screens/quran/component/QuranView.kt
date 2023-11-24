package com.moali.eqraa.presentation.screens.quran.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.eqraa.presentation.screens.quran.component.SouraItem
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranView(
    sour: List<Soura>,
    onSouraItemClick: (Soura) -> Unit,
    onBackClick: () -> Unit,
    onArchiveClick:()->Unit,
) {

    Scaffold (
        topBar = {
            TopAppbar(
                title = stringResource(SharedRes.strings.alquran_alkarem),
                onBackClick = { onBackClick() },
                isBack = true,
                actions = {
                    Icon(
                        modifier = Modifier.size(25.dp)
                            .clickable { onArchiveClick() },
                        imageVector = Icons.Default.Archive,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        }
    ){

        Box (
            Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                items(sour) {
                    SouraItem(
                        soura = it,
                        onClick = {
                            onSouraItemClick(it)
                        }
                    )
                }
            }

        }
    }

}