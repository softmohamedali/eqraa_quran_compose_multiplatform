package com.moali.eqraa.domain.abstractions

interface MediaPlayerOperation {

    fun prepare(pathSource: String, listener: MediaPlayerListener)

    fun start()

    fun pause()

    fun stop()

    fun isPlaying(): Boolean

    fun release()

}