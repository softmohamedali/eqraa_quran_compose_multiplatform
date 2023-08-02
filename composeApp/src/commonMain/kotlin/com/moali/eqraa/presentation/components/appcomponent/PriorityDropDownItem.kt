//package com.moali.eqraa.presentation.components.appcomponent
//
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowDropDown
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.moali.eqraa.domain.models.Priority
//import com.moali.eqraa.ui.theme.none
//
//@Composable
//fun ProrityDropDownItem(
//    priority: Priority,
//    OnItemSelected: (Priority) -> Unit
//) {
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//    val iconArrowState by animateFloatAsState(
//        targetValue = if (expanded) 180f else 0f
//    )
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.background)
//            .clickable {
//                expanded = true
//            }
//            .border(
//                width = 1.dp,
//                color = MaterialTheme.colorScheme.onBackground,
//                shape = MaterialTheme.shapes.small
//            ),
//        verticalAlignment = Alignment.CenterVertically
//    )
//    {
//        Canvas(
//            modifier = Modifier
//                .size(16.dp)
//                .weight(1f)
//        ) {
//            drawCircle(color = priority.color)
//        }
//        Text(
//            modifier = Modifier.weight(8f),
//            text = priority.name,
//            style = TextStyle(fontSize = 16.sp)
//        )
//        IconButton(onClick = { expanded = true }, modifier = Modifier.weight(1f)) {
//            Icon(
//                imageVector = Icons.Filled.ArrowDropDown,
//                contentDescription = "",
//                modifier = Modifier.rotate(iconArrowState),
//                tint = MaterialTheme.colorScheme.onBackground
//            )
//        }
//
//        DropdownMenu(
//            modifier = Modifier
//                .fillMaxWidth(fraction = 0.94f)
//                .background(MaterialTheme.colorScheme.onBackground),
//            expanded = expanded,
//            onDismissRequest = {
//                expanded = false
//            }
//        ) {
//            DropdownMenuItem(onClick = {
//                expanded = false
//                OnItemSelected(Priority.LOW)
//            }) {
//                PriorityItem(priority = Priority.LOW)
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                OnItemSelected(Priority.MEDIUME)
//            }) {
//                PriorityItem(priority = Priority.MEDIUME)
//
//            }
//            DropdownMenuItem(onClick = {
//                expanded = false
//                OnItemSelected(Priority.HIGH)
//            }) {
//                PriorityItem(priority = Priority.HIGH)
//            }
//        }
//    }
//}
//
