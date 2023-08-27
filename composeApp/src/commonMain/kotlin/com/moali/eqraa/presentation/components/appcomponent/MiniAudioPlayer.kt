package com.moali.eqraa.presentation.components.appcomponent

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.painterResource


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MiniAudioPlayer(
    modifier: Modifier=Modifier,
    isAudioPlayed: Boolean,
    currentProgress: Float,
    onItemClick:()->Unit,
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
                color = MaterialTheme.colorScheme.onPrimary,
                backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                progress = currentProgress,
                modifier = Modifier
                    .fillMaxWidth()
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
                    .weight(0.6f)
                    .padding(start = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Headphones,
                    contentDescription = null,
                    tint=MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = artistName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color=MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(top = 6.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(end = 16.dp)
            ) {
                // Previous Button
                IconButton(
                    onClick = {
                        onPreviousClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(SharedRes.images.back),
                        tint =  MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                // Play or Pause Button
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
                                .size(20.dp)
                        )
                    }
                }

                // Next Button
                IconButton(
                    onClick = {
                        onNextClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(SharedRes.images.next),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }
    }
}