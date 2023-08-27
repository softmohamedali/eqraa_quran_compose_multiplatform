package com.moali.eqraa.core.shared

import android.media.MediaPlayer
import android.net.Uri
import co.touchlab.kermit.Logger
import com.moali.eqraa.domain.abstractions.MediaPlayerListener
import com.moali.eqraa.domain.abstractions.MediaPlayerOperation

 actual class MediaPlayerController:MediaPlayerOperation {

    private var mediaPlayer:MediaPlayer?=null

     private var uri: Uri? = null

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
          Logger.i{"start controlleer-------"}
         mediaPlayer?.start()
     }

      override fun pause() {
          Logger.i{"Pause controlleer-------"}
         mediaPlayer?.also { mediaPlayer ->
             if (mediaPlayer.isPlaying) {
                 mediaPlayer.pause()
             }
         }
     }

      override fun stop() {
          Logger.i{"stopp controlleer-------"}
         mediaPlayer?.stop()
     }

      override fun release() {
         isMediaPlayerReady = false
         mediaPlayer?.release()
         mediaPlayer = null
     }

      override fun isPlaying(): Boolean {
         return mediaPlayer?.isPlaying ?: false
     }

     fun convertMillisecondsToTime(milliseconds: Long): String {
         val seconds = (milliseconds / 1000).toInt()
         val hours = seconds / 3600
         val minutes = (seconds % 3600) / 60
         val remainingSeconds = seconds % 60

         return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
     }

}