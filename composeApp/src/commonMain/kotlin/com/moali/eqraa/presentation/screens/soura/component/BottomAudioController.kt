package com.moali.eqraa.presentation.screens.soura.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.rounded.Forward10
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Replay10
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun BottomAudioController(
    modifier: Modifier=Modifier,
    title:String="title",
    subTitle:String="susbTitle",
    isSongPlaying: Boolean=false,
    currentProgress: Float=0.0f,
    totalProgress: Float=0.0f,
    currentTime: String="01:44",
    totalTime: String="00:00",
    onPauseClick: () -> Unit={},
    onResumeClick: () -> Unit={},
    playNextSong: () -> Unit={},
    playPreviousSong: () -> Unit={},
    onSliderChange: (Float) -> Unit={},
    onSliderChangeFinished: () -> Unit={},
    onRewind: () -> Unit={},
    onForward: () -> Unit={},
    onClose: () -> Unit={}
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Column {
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onClose
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = "Close",
                        tint=MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                Row (
                    modifier=Modifier.fillMaxWidth()
                ){
                    VinylAnimation(
                        modifier=Modifier.size(50.dp),
                        isSongPlaying = isSongPlaying
                    )
                    Column(
                        modifier=Modifier.padding(horizontal =8.dp)
                    ) {
                        Text(
                            text = title,
                            color = MaterialTheme.colorScheme.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text=subTitle,
                            color = MaterialTheme.colorScheme.onPrimary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Slider(
                        value = currentProgress,
                        valueRange =  0f..totalProgress,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onValueChange = onSliderChange,
                        onValueChangeFinished = onSliderChangeFinished,
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.medium
                        ) {
                            Text(
                                currentTime,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.medium
                        ) {
                            Text(
                                totalTime,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Replay10,
                        contentDescription = "Replay 10 seconds",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = onRewind)
                            .padding(12.dp)
                            .size(32.dp),
                        tint= MaterialTheme.colorScheme.onPrimary,
                    )
                    Icon(
                        imageVector = if (isSongPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = {
                                if (isSongPlaying) {
                                    onPauseClick()
                                } else {
                                    onResumeClick()
                                }
                            })
                            .size(64.dp)
                            .padding(8.dp),
                        tint= MaterialTheme.colorScheme.onPrimary,
                    )
                    Icon(
                        imageVector = Icons.Rounded.Forward10,
                        contentDescription = "Forward 10 seconds",
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = onForward)
                            .padding(12.dp)
                            .size(32.dp),
                        tint= MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        }
    }
}


