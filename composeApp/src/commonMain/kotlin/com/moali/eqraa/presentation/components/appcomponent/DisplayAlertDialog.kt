//package com.moali.eqraa.presentation.components.appcomponent
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//
//@Composable
//fun DisplayAlertDialog(
//    showAlert: Boolean,
//    title: String,
//    text: String,
//    closeDialog: () -> Unit,
//    confirmClick: () -> Unit
//) {
//    if (showAlert) {
//        AlertDialog(
//            text = {
//                Text(text = text)
//            },
//            title = {
//                Text(text = title, style = MaterialTheme.typography.h5)
//            },
//            confirmButton= {
//                Button(onClick = {
//                    confirmClick()
//                    closeDialog()
//                }) {
//                    Text(text = "YES")
//                }
//            },
//            dismissButton= {
//                Button(onClick = {
//                    closeDialog()
//                }) {
//                    Text(text = "NO")
//                }
//            },
//            onDismissRequest = {closeDialog()}
//        )
//    }
//}