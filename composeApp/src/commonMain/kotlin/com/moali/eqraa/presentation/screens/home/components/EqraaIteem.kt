package com.moali.eqraa.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.presentation.components.TextFullW
import com.moali.eqraa.ui.resources.ContentDescriptions
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun EqraaIteem (
    modifier: Modifier=Modifier,
    icon:ImageVector=Icons.Rounded.Person,
    text:String="demo text",
    backGroundColor:Color=MaterialTheme.colorScheme.secondary,
    onClick:()->Unit={}
){

    val myModifier=modifier.height(200.dp)
        .clip(RoundedCornerShape( 10))
        .background(backGroundColor)
        .clickable { onClick() }

    Box(
        modifier=myModifier
    ){
        Column (
            modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                imageVector = icon,
                contentDescription = ContentDescriptions.EQRAA_ITEM,
                modifier=Modifier.size(70.dp).weight(5f),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            TextFullW(
                modifier=Modifier.weight(2f),
                text = text,
                fontSize = 18.sp
            )

        }
    }
}


@Composable
fun EqraaIteem (
    modifier: Modifier=Modifier,
    icon: Painter ,
    text:String="demo text",
    backGroundColor:Color=MaterialTheme.colorScheme.secondary,
    onClick:()->Unit={},
){

    val myModifier=modifier.height(200.dp)
        .clip(RoundedCornerShape( 10))
        .background(backGroundColor)
        .clickable { onClick() }

    Box(
        modifier=myModifier
    ){
        Column (
            modifier=Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = icon,
                contentDescription = ContentDescriptions.EQRAA_ITEM,
                modifier=Modifier.size(70.dp).weight(5f),
            )
            TextFullW(
                modifier=Modifier.weight(2f),
                text = text,
                fontSize = 18.sp
            )

        }
    }
}

