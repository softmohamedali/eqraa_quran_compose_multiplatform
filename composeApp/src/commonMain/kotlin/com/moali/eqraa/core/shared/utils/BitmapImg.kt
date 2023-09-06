package com.moali.eqraa.core.shared.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun rememberBitmapFromByteArray(byteArray: ByteArray?):ImageBitmap?


