package com.moali.eqraa.domain.abstractions.media

interface MediaPlayerListener {
    fun onReady()
    fun onVideoCompleted()
    fun onError()
}