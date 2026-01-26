package kz.rymbek.platform.common.core.player.playback

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import kz.rymbek.platform.common.core.player.playback.media_session.AppMediaSessionService

@UnstableApi
class AppMediaController(
    context: Context,
) {
    val sessionToken =
        SessionToken(context, ComponentName(context, AppMediaSessionService::class.java))

    private val controllerFuture = MediaController
        .Builder(context, sessionToken)
        .buildAsync()

    private val mediaController: MediaController?
        get() = if (controllerFuture.isDone && !controllerFuture.isCancelled) controllerFuture.get() else null


    init {
        controllerFuture.addListener(
            {
            },
            MoreExecutors.directExecutor()
        )
    }

    fun playPause() {
        val controller = mediaController ?: return
        if (controller.isPlaying) controller.pause() else controller.play()
    }

    fun play() {
        mediaController?.play()
    }

    fun pause() {
        mediaController?.pause()
    }

    fun setMediaItems(items: List<MediaItem>) {
        mediaController?.apply {
            setMediaItems(items)
            prepare()
            play()
        }
    }
}