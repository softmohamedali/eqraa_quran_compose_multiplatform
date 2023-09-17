package com.moali.eqraa.presentation.screens.soura.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.domain.models.supSouraAyat
import com.moali.eqraa.presentation.components.LoadingLayer
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SouraView(
    isLoadingMedia:Boolean,
    isLoading:Boolean,
    soura: Soura,
    onBackClick: () -> Unit,
    isAudioPlayed: Boolean,
    currentProgress: Float=0f,
    totalProgress: Float=0f,
    totalTime:String="",
    currentTime:String="",
    isShowBottomSheet: Boolean,
    onItemClick: () -> Unit={},
    onPreviousClick: () -> Unit = {},
    onPauseClick: () -> Unit = {},
    onResumeClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onSliderChange: (Float) -> Unit={},
    onSliderChangeFinished: () -> Unit={},
    onRewind: () -> Unit={},
    onForward: () -> Unit={},
    onClose: () -> Unit={}

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

        Box {
            Column(
                modifier = Modifier.fillMaxSize().padding(it),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(10.dp)
                        .verticalScroll(scrollableState).weight(1f),
                ) {
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = " ⊰⟪ ${soura.name} ⟫⊱ " ,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    if(soura.id!=9&&soura.id!=1){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = " ⊰⟪ بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم ⟫⊱ " ,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = supSouraAyat(soura.soura),
                        overflow = TextOverflow.Visible,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            textDirection = TextDirection.Rtl,
                        )

                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "⊰⟪ صدق اللَّهُ العظيم ⟫⊱",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                }

                if (!isShowBottomSheet){
                    MiniAudioPlayer(
                        isLoading=isLoadingMedia,
                        modifier = Modifier.weight(0.12f),
                        isAudioPlayed = isAudioPlayed,
                        currentProgress = currentProgress,
                        onItemClick = onItemClick,
                        imageAudioPlay = "",
                        title = soura.name,
                        totalProgress = totalProgress,
                        artistName = "ElShes8 Mashari Alafaisi",
                        onPreviousClick = onPreviousClick,
                        onPauseClick = onPauseClick,
                        onResumeClick = onResumeClick,
                        onNextClick = onNextClick
                    )
                }else{
                    BottomAudioController(
                        isLoading=isLoadingMedia,
                        modifier = Modifier.weight(0.5f),
                        title = soura.name,
                        subTitle = "ElShes8 Mashari Alafaisi",
                        isSongPlaying = isAudioPlayed,
                        currentProgress = currentProgress,
                        totalProgress = totalProgress,
                        currentTime = currentTime,
                        totalTime = totalTime,
                        onPauseClick = onPauseClick,
                        onResumeClick = onResumeClick,
                        playNextSong = onNextClick,
                        playPreviousSong = onPreviousClick,
                        onSliderChange = onSliderChange,
                        onSliderChangeFinished = onSliderChangeFinished,
                        onRewind = onRewind,
                        onForward = onForward,
                        onClose = onClose
                    )
                }
            }
            if (isLoading){
                LoadingLayer(color = MaterialTheme.colorScheme.primary)
            }

        }

    }


}