package com.moali.eqraa.presentation.components.appcomponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moali.eqraa.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(
    title: String,
    onBackClick: () -> Unit = {},
    isBack: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier,
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.onPrimary,
                style = Typography.headlineSmall
            )
        },
        actions={
            actions()
        },
        navigationIcon = {
            if (isBack) {
                Icon(
                    modifier = Modifier.size(25.dp)
                        .clickable { onBackClick() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
    )
//    Surface(
//        modifier = Modifier.height(60.dp).shadow(elevation = 2.dp)
//            ,
//        color =MaterialTheme.colorScheme.primary
//    ){
//        Row(
//            modifier = Modifier.fillMaxWidth().padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            if (isBack){
//                Icon(
//                    modifier = Modifier.size(25.dp).weight(1f)
//                        .clickable { onBackClick() },
//                    imageVector = Icons.Default.ArrowBack,
//                    //ToDo content discription
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//            Text(
//                text = title,
//                modifier = Modifier.weight(8f),
//                textAlign = TextAlign.End,
//                color = MaterialTheme.colorScheme.onPrimary,
//                style = Typography.headlineSmall
//            )
//        }
//    }
}