package com.moali.eqraa.presentation.screens.soura.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.Resources
import com.moali.eqraa.core.shared.ui.AdmobBanner
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.components.LoadingLayer
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.eqraa.presentation.screens.soura.SouraMapData
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi


@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun SouraView(
    isLoadingMedia:Boolean,
    isLoading:Boolean,
    soura: Soura,
    lang:String,
    onBackClick: () -> Unit,
    isAudioPlayed: Boolean,
    souraMap: SouraMapData,
    currentProgress: Float=0f,
    totalProgress: Float=0f,
    scrollPostion: Int=0,
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
    onClose: () -> Unit={},
    onAddReferenceClick:(scrollValue:Int,souraId:Int)->Unit

) {

    val scrollableState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(scrollPostion){
        scrollableState.scrollTo(value = scrollPostion)
    }

    Scaffold(
        snackbarHost={ SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppbar(
                title = "${stringResource(SharedRes.strings.soura_)} ${if (lang=="ar") soura.name_ar else soura.name}",
                onBackClick = { onBackClick() },
                isBack = true,
                actions = {
                    val text=stringResource(SharedRes.strings.A_bookmark_has_been_saved)
                    Image(
                        modifier = Modifier.size(25.dp)
                            .clickable {
                                onAddReferenceClick(
                                    scrollableState.value,
                                    soura.id
                                )
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(text)
                                }
                            },
                        painter = dev.icerock.moko.resources.compose.painterResource(SharedRes.images.bookmark),
                        contentDescription = null,
                    )
                }
            )
        },

    ) {
        Box (
            modifier = Modifier.fillMaxSize().padding(it)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(5.dp)
                        .verticalScroll(scrollableState).weight(1f),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image(
                            modifier=Modifier.fillMaxWidth().height(70.dp),
                            painter = painterResource(SharedRes.images.border2),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            colorFilter= ColorFilter.tint( LocalContentColor.current)
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(SharedRes.strings.soura_)+" "+if (lang=="ar") soura.name_ar else soura.name ,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    if(soura.id!=9&&soura.id!=1){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "  بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم  " ,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    RichTextComponent(soura, souraMap = souraMap.souraMap,lang)
//                    Text(
//                        modifier = Modifier.fillMaxWidth(),
//                        text = supSouraAyat(soura.soura),
//                        overflow = TextOverflow.Visible,
//                        textAlign = TextAlign.Center,
//                        fontSize = 24.sp,
//                        lineHeight = 40.sp,
//                        fontWeight = FontWeight.Bold,
//                        style = TextStyle(
//                            textDirection = TextDirection.Rtl,
//                        )
//
//                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image(
                            modifier=Modifier.fillMaxWidth().height(70.dp),
                            painter = painterResource(SharedRes.images.border2),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            colorFilter= ColorFilter.tint( LocalContentColor.current)
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "صدق اللَّهُ العظيم",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                        )
                    }


                }
                AdmobBanner(modifier=Modifier.fillMaxWidth(),"ca-app-pub-7258529419894486/4757289034")
                if (!isShowBottomSheet){
                    MiniAudioPlayer(
                        isLoading=isLoadingMedia,
                        modifier = Modifier.weight(0.12f),
                        isAudioPlayed = isAudioPlayed,
                        currentProgress = currentProgress,
                        onItemClick = onItemClick,
                        imageAudioPlay = "",
                        title = if (lang=="ar") soura.name_ar else soura.name,
                        totalProgress = totalProgress,
                        artistName = stringResource(SharedRes.strings.sheskh_name),
                        onPreviousClick = onPreviousClick,
                        onPauseClick = onPauseClick,
                        onResumeClick = onResumeClick,
                        onNextClick = onNextClick
                    )
                }else{
                    BottomAudioController(
                        isLoading=isLoadingMedia,
                        modifier = Modifier.weight(0.5f),
                        title = if (lang=="ar") soura.name_ar else soura.name,
                        subTitle = stringResource(SharedRes.strings.sheskh_name),
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



@OptIn(ExperimentalResourceApi::class)
@Composable
fun AyaNum(number: String) {
    Box(
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier=Modifier.size(60.dp),
            painter = org.jetbrains.compose.resources.painterResource(Resources.images.aya),
            contentDescription = null
        )
        Text(
            number,
            color = Color.Black,
            fontSize = 15.sp,
        )
    }
}
@Composable
fun RichTextComponent(soura: Soura,souraMap:Map<String, InlineTextContent>,lang:String) {

    val asd= remember{ mutableStateOf<AnnotatedString>(AnnotatedString("")) }
    log("----------------------ppppppppppppppppppppp")
    LaunchedEffect(1){
        log("----------------------p5555555555555555555p")
        var x=buildAnnotatedString {
            for (i in 0 until soura.soura.size) {
                log("----------------------${soura.soura[i].standard_full}")
                withStyle(
                    style = SpanStyle(
                        fontSize = 25.sp,
                    )
                ) {
                    append(" ${soura.soura[i].standard_full}")
                    appendInlineContent(id = "imageId${i+1}")
                }
            }
        }
        asd.value=x
    }

    Text(
        text =buildAnnotatedString {
            for (i in 0 until soura.soura.size) {
                log("----------------------${soura.soura[i].standard_full}")
                withStyle(
                    style = SpanStyle(
                        fontSize = 25.sp,
                    )
                ) {
                    append(" ${soura.soura[i].standard_full}")
                    appendInlineContent(id = "imageId${i+1}")
                }
            }
        },
        textAlign = TextAlign.Center,
        inlineContent =souraMap ,
        lineHeight = 45.sp,
        style = TextStyle(
            textDirection = TextDirection.Rtl
        )
    )
}



