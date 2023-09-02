package com.moali.eqraa.core.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import com.moali.eqraa.R
import com.moali.eqraa.core.reiver.NotificationActionBroadcastReceiver
import com.moali.eqraa.core.utils.log
import com.moali.eqraa.domain.abstractions.MediaPlayerOperation
import com.moali.eqraa.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class AudioServices() : Service(), KoinComponent {

    private val mediaPlayerController: MediaPlayerOperation by inject()

    private lateinit var mediaSessionCompat: MediaSessionCompat

    var audioNotification: Notification? = null

    private var job: Job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var coroutineScope = CoroutineScope(coroutineContext)


    companion object {
        private const val MEDIA_SESSION_ACTIONS = (PlaybackStateCompat.ACTION_PLAY
                or PlaybackStateCompat.ACTION_PAUSE
                or PlaybackStateCompat.ACTION_PLAY_PAUSE
                or PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                or PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                or PlaybackStateCompat.ACTION_STOP
                or PlaybackStateCompat.ACTION_SEEK_TO)
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaSessionCompat = MediaSessionCompat(this, "Music")
        mediaSessionCompat.isActive = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand ${intent?.action}", "services warf")
        when (intent?.action) {
            ActionAudioService.START.toString() -> startService()
            ActionAudioService.STOP.toString() -> stopSelf()
            ActionAudioService.PLAY_PAUSE.toString() -> playPause()
            ActionAudioService.SKIP10.toString() -> skip10Sec()
            ActionAudioService.BACK10.toString() -> back10Sec()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startService() {
        setMediaSessionAction()
        setMediaSessionMetaData()
        coroutineScope.launch {
            mediaPlayerController.playPauseState.collect{
                createNotification(it)
            }
        }

    }


    fun playPause() {
        if (!mediaPlayerController.isPlaying()) {
            mediaPlayerController.start()
        } else {
            mediaPlayerController.pause()
        }
    }

    fun skip10Sec() {
        mediaPlayerController.skip10Sec()
    }

    fun back10Sec() {
        mediaPlayerController.back10Sec()
    }


    private fun createNotification(
        isPlaying:Boolean
    ){
        val mainIntent = Intent(this, MainActivity::class.java).also {
            it.action = ActionAudioService.START.toString()
        }
        val mainPendingIntent = PendingIntent.getActivity(
            this, 0, mainIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val back10Intent = Intent(this, NotificationActionBroadcastReceiver::class.java).also {
            it.action = ActionAudioService.BACK10.toString()
        }
        val back10PendingIntent =
            PendingIntent.getBroadcast(this, 0, back10Intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val playPAUSEPauseIntent =
            Intent(this, NotificationActionBroadcastReceiver::class.java).also {
                it.action = ActionAudioService.PLAY_PAUSE.toString()
            }
        val playPausePendingIntent =
            PendingIntent.getBroadcast(
                this,
                0,
                playPAUSEPauseIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val skip10Intent = Intent(this, NotificationActionBroadcastReceiver::class.java).also {
            it.action = ActionAudioService.SKIP10.toString()
        }
        val skip10PendingIntent =
            PendingIntent.getBroadcast(this, 0, skip10Intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val playPauseIcon=if (isPlaying)
            R.drawable.pause
        else R.drawable.play_arrow
        //       val backGroundLargeIcon=BitmapFactory.decodeResource(this.resources, R.drawable.ic_album)
        val audioNotification=NotificationCompat.Builder(this,"audio_notifi")
            .setOngoing(true).apply {
                setContentIntent(mainPendingIntent)
                priority = NotificationCompat.PRIORITY_MAX
                setCategory(NotificationCompat.CATEGORY_SERVICE)
                setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                setSmallIcon(androidx.core.R.drawable.ic_call_answer)
                setContentTitle("title")
                setContentText("currentSong!!.artistName")
                setSubText("getSubText()")
//                setOngoing(mediaPlayerController.isPlaying())
                addAction(R.drawable.back_10, "Back_10", back10PendingIntent)
                addAction(
                    playPauseIcon, "Play", playPausePendingIntent
                )
                addAction(R.drawable.forward_10, "Next_10", skip10PendingIntent)
                setSmallIcon(R.drawable.music)
                setShowWhen(false)
                setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setMediaSession(mediaSessionCompat.sessionToken)
                        .setShowActionsInCompactView(0, 1, 2)
                )
                //setLargeIcon(backGroundLargeIcon)
            }
        startForeground(1,audioNotification.build())
    }


    fun setMediaSessionAction() {
        val stateBuilder = PlaybackStateCompat.Builder()
            .setActions(MEDIA_SESSION_ACTIONS)

        mediaSessionCompat.setPlaybackState(stateBuilder.build())
    }

    private fun setMediaSessionMetaData() {

        val metadata = MediaMetadataCompat.Builder().apply {
            putString(MediaMetadataCompat.METADATA_KEY_TITLE, "song.name")
            putString(MediaMetadataCompat.METADATA_KEY_ALBUM, "song.albumName")
            putString(MediaMetadataCompat.METADATA_KEY_ARTIST, "song.artistName")
            putLong(MediaMetadataCompat.METADATA_KEY_DURATION, 0)
        }
        mediaSessionCompat.setMetadata(metadata.build())
    }
    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayerController.isPlaying()) {
            mediaPlayerController.stop()
            stopSelf()
        }
    }
}