package com.moali.eqraa.core.shared.utils

import com.moali.eqraa.domain.abstractions.media.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.media.MediaPlayerOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

actual class MediaPlayerController: MediaPlayerOperation {
    override fun prepare(pathSource: String, listener: MediaPlayerListener) {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun isPlaying(): Boolean {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun getDuration():Int?{
        return 0
    }

    override fun getCurrentPosition(): Flow<Int?> {
        return flow { }
    }
    override val current: MutableStateFlow<Int?> = MutableStateFlow(0)
    override val playPauseState: MutableStateFlow<Boolean>
        get() = TODO("Not yet implemented")

    override fun navigateTo(duration: Int){}

    override fun skip10Sec(){}

    override fun back10Sec(){}

    override fun setSpeed(speed: Float){}
}