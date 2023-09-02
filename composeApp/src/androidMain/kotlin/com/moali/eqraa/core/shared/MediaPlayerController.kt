package com.moali.eqraa.core.shared

import android.media.MediaPlayer
import android.net.Uri
import co.touchlab.kermit.Logger
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.domain.abstractions.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.MediaPlayerOperation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

actual class MediaPlayerController : MediaPlayerOperation {

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

    private lateinit var listener: MediaPlayerListener

    private fun onReady() {
        listener.onReady()
    }

    override fun prepare(pathSource: String, listener: MediaPlayerListener) {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }
        this.listener = listener
        this.uri = Uri.parse(pathSource)
        mediaPlayer = MediaPlayer().apply {
            setOnCompletionListener { this@MediaPlayerController.listener.onVideoCompleted() }
            setOnPreparedListener { this@MediaPlayerController.listener.onReady() }
            setOnErrorListener { mediaPlayer, i, i2 ->
                this@MediaPlayerController.listener.onError()
                mediaPlayer.release()
                true
            }
        }
        mediaPlayer?.setDataSource(pathSource)
        mediaPlayer?.prepareAsync()



    }

    override fun start() {
        mediaPlayer?.start()
        playPauseState.value=true
        coroutineScope.launch {
            updateCurrentDuration()
        }
    }

    override fun pause() {
        mediaPlayer?.also { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                playPauseState.value=false
            }
        }
    }

    override fun stop() {
        mediaPlayer?.stop()
        playPauseState.value=false
        coroutineScope.cancel()
    }

    override fun release() {
        isMediaPlayerReady = false
        playPauseState.value=false
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun isPlaying(): Boolean {
        playPauseState.value=mediaPlayer?.isPlaying ?: false
        return mediaPlayer?.isPlaying ?: false
    }

    override fun getDuration(): Int? {
        if (mediaPlayer!=null){
            return mediaPlayer?.duration
        }
        else return null
    }

    override fun getCurrentPosition(): Flow<Int?> {
        return flow {
            while (mediaPlayer?.currentPosition!! < mediaPlayer?.duration!!) {
                delay(1000)
                mediaPlayer?.currentPosition
            }
        }

    }

    override fun navigateTo(duration: Int) {
        current.value = duration
        mediaPlayer?.seekTo(duration )
    }

    override fun skip10Sec() {
        current.value = current.value?.plus(10000)
        current.value?.let { mediaPlayer?.seekTo(it) }
    }

    override fun back10Sec() {
        current.value = current.value?.minus(10000)
        if (current.value!! < 0) {
            current.value = 0
        }
        mediaPlayer?.seekTo(current.value!!)
    }

    override fun setSpeed(speed: Float) {
        mediaPlayer?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                it.playbackParams = it.playbackParams.setSpeed(speed)
            }
        }
    }


    private suspend fun updateCurrentDuration() {
        while (true) {
            delay(1000) // Update every second
            mediaPlayer?.let {
                if (it.isPlaying) {
                    current.value=mediaPlayer!!.currentPosition
                }
            }
        }
    }


}