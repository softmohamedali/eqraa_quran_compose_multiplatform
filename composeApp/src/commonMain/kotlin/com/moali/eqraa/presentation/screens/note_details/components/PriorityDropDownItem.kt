package com.moali.eqraa.presentation.screens.note_details.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.domain.models.Priority
import com.moali.eqraa.presentation.screens.notes.components.PriorityMenu
import com.moali.eqraa.ui.theme.none
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ProrityDropDownItem(
    priority: Priority,
    onItemSelected: (Priority) -> Unit,
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onClick: () -> Unit
) {

    val iconArrowState by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
                .clickable {
                    onClick()
                }
                .border(
                    width = 1.dp,
                    color = none.copy(ContentAlpha.medium),
                    shape = MaterialTheme.shapes.small
                ),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Canvas(
                modifier = Modifier
                    .size(16.dp)
                    .weight(1f)
            ) {
                drawCircle(color = priority.color)
            }
            Text(
                modifier = Modifier.weight(8f),
                text = stringResource(priority.text),
                style = TextStyle(fontSize = 16.sp)
            )
            IconButton(onClick = { onClick() }, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "",
                    modifier = Modifier.rotate(iconArrowState),
                    tint = MaterialTheme.colors.onPrimary
                )
            }

        }
        if (isExpanded) {
            PriorityMenu(
                onClick = {
                    onItemSelected(it)
                },
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.94f)
                    .background(MaterialTheme.colors.background),

                showNone = false
            )
        }
    }
}
