package com.moali.eqraa.presentation.screens.soura

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.moali.eqraa.di.viewModelModule
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.components.appcomponent.MiniAudioPlayer
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.eqraa.presentation.screens.quran.component.SouraItem
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SouraView(
    soura: Soura,
    onBackClick: () -> Unit,
    isAudioPlayed: Boolean,
    currentProgress: Float,
    onItemClick:()->Unit,
    onPreviousClick:()->Unit={},
    onPauseClick:()->Unit={},
    onResumeClick:()->Unit={},
    onNextClick: () -> Unit={}
) {

    val scrollableState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppbar(
                title = "سورة ${soura.name}",
                onBackClick = { onBackClick() },
                isBack = true
            )
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(it),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp)
                    .verticalScroll(scrollableState).weight(9f),
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = soura.name,
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم",
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = soura.sup(),
                    overflow = TextOverflow.Visible,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    lineHeight = 40.sp,
                    style = TextStyle(
                        textDirection = TextDirection.Rtl,
                    )

                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "صدق اللَّهُ العظيم",
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                )
            }

            MiniAudioPlayer(
                modifier = Modifier.weight(1f),
                isAudioPlayed = isAudioPlayed,
                currentProgress = currentProgress,
                onItemClick = onItemClick,
                imageAudioPlay = "",
                title = soura.name,
                artistName = "Mashari afaisi",
                onPreviousClick = onPreviousClick,
                onPauseClick = onPauseClick,
                onResumeClick = onResumeClick,
                onNextClick = onNextClick
            )

        }
    }


}