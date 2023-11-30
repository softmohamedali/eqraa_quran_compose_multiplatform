package com.moali.eqraa.presentation.screens.juza

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
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
import com.moali.eqraa.domain.models.Juza
import com.moali.eqraa.domain.models.Soura
import com.moali.eqraa.presentation.components.LoadingLayer
import com.moali.eqraa.presentation.components.appcomponent.TopAppbar
import com.moali.kmm_sharingresources.SharedRes
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun JuzaView(
    isLoading:Boolean,
    juza: Juza,
    onBackClick: () -> Unit,
    scrollPostion: Int=0,
    onAddReferenceClick:(scrollValue:Int,souraId:Int)->Unit

) {

    val scrollableState = rememberScrollState()

    LaunchedEffect(scrollPostion){
        scrollableState.scrollTo(value = scrollPostion)
    }

    Scaffold(
        topBar = {
            TopAppbar(
                title = "juza ${juza.id}",
                onBackClick = { onBackClick() },
                isBack = true,
                actions = {
                    Image(
                        modifier = Modifier.size(25.dp)
                            .clickable {
                                onAddReferenceClick(
                                    scrollableState.value,
                                    juza.id
                                )
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

                    juza.sour.forEach {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Image(
                                modifier=Modifier.fillMaxWidth(),
                                painter = painterResource(Resources.images.border),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = it.name_ar ,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                        if(it.id!=9&&it.id!=1){
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "  بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيم  " ,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        RichTextComponent(it)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image(
                            modifier=Modifier.fillMaxWidth(),
                            painter = painterResource(Resources.images.border),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
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
                AdmobBanner(modifier=Modifier.fillMaxWidth())
            }
            if (isLoading){
                LoadingLayer(color = MaterialTheme.colorScheme.primary)
            }

        }

    }


}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AyaNum(number: Int) {
    Box(
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier=Modifier.size(60.dp),
            painter = painterResource(Resources.images.aya),
            contentDescription = null
        )
        Text(
            number.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 15.sp
        )
    }
}
@Composable
fun RichTextComponent(soura: Soura) {
    Text(
        text = buildAnnotatedString {
            for (i in soura.soura) {
                withStyle(
                    style = SpanStyle(
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    append(" ${i.standard_full}")
                    appendInlineContent(id = "${i.sura_id}imageId${i.aya_id}")
                }
            }
        },
        textAlign = TextAlign.Center,
        inlineContent = generateInlineContent(soura),
        lineHeight = 45.sp,
        style = TextStyle(
            textDirection = TextDirection.Rtl
        )
    )
}

fun generateInlineContent(soura: Soura): Map<String, InlineTextContent> {
    val inlineContent = mutableMapOf<String, InlineTextContent>()
    for (i in soura.soura) {
        inlineContent["${i.sura_id}imageId${i.aya_id}"] =
            InlineTextContent(Placeholder(50.sp, 50.sp, PlaceholderVerticalAlign.TextCenter)) {
                AyaNum(i.aya_id)
            }
    }
    return inlineContent
}
