package com.moali.eqraa.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconTextField(
    value: String = "",
    label: String,
    onValueChange: (String) -> Unit,
    myModifier: Modifier = Modifier.fillMaxWidth(),
    icon: ImageVector=Icons.Default.Person,
    onClick:()->Unit={},
    onIconClick:()->Unit={},
    readOnly:Boolean=false,
    isError:Boolean=false,
    error:String?=null,
    keyboardType:KeyboardType=KeyboardType.Text,
    secoundIcon: ImageVector=Icons.Default.Person,
    onSecoundIconClick:()->Unit={}
) {
    Column(
        modifier = myModifier.clickable { onClick() }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            readOnly = readOnly,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.onSecondary),
            singleLine = true,
            label = { Text(text = label) },
            textStyle = TextStyle(color = Color.DarkGray),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = {
                IconButton(
                    onClick = {onIconClick() },
                    content = {Icon(
                        imageVector = icon,
                        contentDescription = "Search"
                    )}
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {onSecoundIconClick() },
                    content = {
                        if (secoundIcon!=null){
                            Icon(
                                imageVector = secoundIcon,
                                contentDescription = "Search",

                            )
                        }
                    }
                )
            },
            isError = isError
        )
        if (isError){
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = error?:"", color = MaterialTheme.colorScheme.error)
        }
    }
}



