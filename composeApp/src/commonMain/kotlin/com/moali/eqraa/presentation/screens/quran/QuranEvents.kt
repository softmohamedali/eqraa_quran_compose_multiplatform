package com.moali.eqraa.presentation.screens.quran

sealed class QuranEvents {
    class OnSearchQueryChange(var query:String) : QuranEvents()
    object OnCloseSearchClick : QuranEvents()
    object OnSearchClick : QuranEvents()
}