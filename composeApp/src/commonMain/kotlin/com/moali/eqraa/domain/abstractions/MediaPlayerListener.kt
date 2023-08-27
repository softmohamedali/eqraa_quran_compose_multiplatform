package com.moali.eqraa.domain.abstractions

interface MediaPlayerListener {
    fun onReady()
    fun onVideoCompleted()
    fun onError()
}