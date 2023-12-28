package com.moali.eqraa.core.shared.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun AdmobBanner(modifier: Modifier,key:String)
@Composable
expect fun InterstitialBanner(modifier: Modifier)