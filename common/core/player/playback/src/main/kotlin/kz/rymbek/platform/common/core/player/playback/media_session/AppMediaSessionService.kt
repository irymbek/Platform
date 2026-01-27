package kz.rymbek.platform.common.core.player.playback.media_session

import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import org.koin.core.annotation.Single

@UnstableApi
@Single
class AppMediaSessionService : MediaSessionService() {
    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(this)
            .setAudioAttributes(MediaSessionServiceConfig.createAudioAttributes(), true)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(MediaSessionServiceConfig.createTrackSelector(this))
            .setLoadControl(MediaSessionServiceConfig.createLoadControl())
            .setMediaSourceFactory(
                DefaultMediaSourceFactory(
                    MediaSessionServiceConfig.createCacheDataSourceFactory(
                        this
                    )
                )
            )
            .setRenderersFactory(MediaSessionServiceConfig.createRenderersFactory(this))
            .build()
    }

    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSession.Builder(this, player).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession

    override fun onDestroy() {
        super.onDestroy()
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
    }
}