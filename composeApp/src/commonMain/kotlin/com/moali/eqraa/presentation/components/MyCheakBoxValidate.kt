package com.moali.eqraa.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MyCheckBoxValidate(
    value:Boolean,
    isError:Boolean=false,
    error:String?=null,
    onCheckedChange:(Boolean)-> Unit,
    modifier: Modifier=Modifier.fillMaxWidth()
){
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked =value, onCheckedChange ={onCheckedChange(it)} )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text ="I Read and accepte Privace and Terms")
        }
        if (isError){
            Text(
                text = error?:"",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}