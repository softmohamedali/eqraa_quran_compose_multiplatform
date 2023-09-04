package com.moali.eqraa.core.shared

import com.moali.eqraa.domain.abstractions.media.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.media.MediaPlayerOperation
import com.sun.jndi.toolkit.url.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import uk.co.caprica.vlcj.player.base.MediaPlayer
import kotlin.coroutines.CoroutineContext

actual class MediaPlayerController: MediaPlayerOperation {

    private var mediaPlayer: MediaPlayer? = null

    private var uri: Uri? = null

    override val current: MutableStateFlow<Int?> = MutableStateFlow(0)

    override val playPauseState: MutableStateFlow<Boolean> = MutableStateFlow(false)


    private var job: Job = Job()
    val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var coroutineScope = CoroutineScope(coroutineContext)
    private var isSurfaceReady = false
        set(value) {
            field = value
            if (value.and(isMediaPlayerReady)) onReady()
        }
    private var isMediaPlayerReady = false
        set(value) {
            field = value
            if (value.and(isSurfaceReady)) onReady()
        }

    private fun onReady() {
        listener.onReady()
    }

    private lateinit var listener: MediaPlayerListener
    override fun prepare(pathSource: String, listener: MediaPlayerListener) {

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
        return flow {  }
    }

    override fun navigateTo(duration: Int){}

    override fun skip10Sec(){}

    override fun back10Sec(){}

    override fun setSpeed(speed: Float){}
}