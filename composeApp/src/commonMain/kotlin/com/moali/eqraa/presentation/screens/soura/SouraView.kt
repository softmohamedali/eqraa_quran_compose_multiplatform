package com.moali.eqraa.presentation.screens.soura

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar


@Composable
fun SouraView(
    soura:Soura,
    onBackClick:()->Unit
) {

    LaunchedEffect(1) {
        Logger.i {
            soura.sup()
        }
    }
    Column (
        modifier = Modifier.fillMaxSize(),
    ){
        TopAppbar(
            title = "سورة ${soura.name}",
            onBackClick={onBackClick()},
            isBack = true
        )

        Column (
            modifier = Modifier.fillMaxSize().padding(10.dp)
        ){
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
                style = TextStyle(
                    textDirection = TextDirection.Rtl
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


    }


}