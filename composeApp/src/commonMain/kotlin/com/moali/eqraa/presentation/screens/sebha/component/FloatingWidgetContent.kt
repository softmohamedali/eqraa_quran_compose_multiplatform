package com.moali.eqraa.presentation.screens.sebha.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FloatingSebhaContent(
    modifier: Modifier=Modifier,
    count: Int=0,
    title:String="",
    width:Int=50,
    onclick:()->Unit={}
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = count.toString(),
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp)


        )
        Spacer(modifier=Modifier.height(5.dp))
        Text(
            text = title,
            fontSize =20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier
                .width(width.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp).clickable {
                    onclick()
                },


        )
    }
}