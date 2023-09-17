package com.moali.eqraa.presentation.screens.soura.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.presentation.components.LoadingLayer


@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun MiniAudioPlayer(
    isLoading:Boolean=false,
    modifier: Modifier=Modifier,
    isAudioPlayed: Boolean=false,
    currentProgress: Float=0f,
    totalProgress:Float=0f,
    onItemClick:()->Unit={},
    imageAudioPlay:String="",
    title:String="",
    artistName:String="",
    onPreviousClick:()->Unit={},
    onPauseClick:()->Unit={},
    onResumeClick:()->Unit={},
    onNextClick: () -> Unit={}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.primary)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource(),
                onClick = onItemClick
            )
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary.copy(0.3f),
                modifier = Modifier
                    .fillMaxWidth()
            )

            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                progress = if((currentProgress/totalProgress).isNaN())0f else (currentProgress/totalProgress) ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {

            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                VinylAnimation(
                    isSongPlaying=isAudioPlayed
                )
                Text(
                    text = "$title : $artistName",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 10.dp)
                        .basicMarquee()
                )
            }

            Row(
                modifier = Modifier
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
//                // Previous Button
//                IconButton(
//                    onClick = {
//                        onPreviousClick()
//                    }
//                ) {
//                    Icon(
//                        painter = painterResource(SharedRes.images.back),
//                        tint =  MaterialTheme.colorScheme.onPrimary,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(20.dp)
//                    )
//                }
                // Play or Pause Button
                if(isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.semantics {
                            this.contentDescription=""
                        },
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }else{
                    IconButton(
                        onClick = {
                            if (isAudioPlayed) {
                                onPauseClick()
                            } else {
                                onResumeClick()
                            }
                        }
                    ) {
                        AnimatedContent(
                            targetState = isAudioPlayed,
                            transitionSpec = {
                                scaleIn(animationSpec = tween(300)) with
                                        scaleOut(animationSpec = tween(200))
                            }
                        ) { target ->
                            Icon(
                                imageVector = if (target) Icons.Default.Pause else Icons.Default.PlayArrow,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    }
                }


                // Next Button
//                IconButton(
//                    onClick = {
//                        onNextClick()
//                    }
//                ) {
//                    Icon(
//                        painter = painterResource(SharedRes.images.next),
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(20.dp)
//                    )
//                }
            }
        }
    }
}