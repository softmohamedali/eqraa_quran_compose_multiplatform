package com.moali.eqraa.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moali.eqraa.presentation.components.AlertDialogContent
import com.moali.kmm_sharingresources.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun SupportDialog(
    isShow:Boolean,
    onCloseClick:()->Unit,
    onShareClick:()->Unit,
    onGoToSupport:()->Unit
) {

    if (isShow){
        AlertDialogContent(
            showAlert = isShow,
            content = {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(modifier= Modifier.weight(1f))
                        Icon(
                            modifier= Modifier.clickable {
                                onCloseClick()
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )

                    }
                    Text(
                        text= stringResource(SharedRes.strings.support_app_owner),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier= Modifier.height(15.dp))
                    Image(
                        modifier= Modifier.size(90.dp),
                        painter = painterResource(SharedRes.images.coffe),
                        contentDescription = null
                    )

                    Spacer(modifier= Modifier.height(20.dp))
                    Text(
                        text= stringResource(SharedRes.strings.support_message),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier= Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        VipButton(
                            modifier=Modifier.weight(1f),
                            text =stringResource(SharedRes.strings.go_to_support),
                            onclick = onGoToSupport,
                            icon = SharedRes.images.coffe
                        )
                        Spacer(Modifier.width(8.dp))
                        VipButton(
                            text =stringResource(SharedRes.strings.share_support_link),
                            onclick = onShareClick ,
                            modifier=Modifier.weight(1f),
                            icon = SharedRes.images.share
                        )
                    }
                }
            }
        )
    }

}


@Composable
fun VipButton(
    text:String="",
    modifier: Modifier = Modifier.alpha(1f),
    corner:Int=15,
    icon: ImageResource,
    enable:Boolean=true,
    onclick:()->Unit,
){

    Button(
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(size = corner.dp))
        ,
        onClick = onclick,
        colors= ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.primary
        ),
        enabled = enable
    ) {
        Row (
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier=Modifier.size(20.dp),
                contentDescription = null,
                painter = painterResource(icon)
            )
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
