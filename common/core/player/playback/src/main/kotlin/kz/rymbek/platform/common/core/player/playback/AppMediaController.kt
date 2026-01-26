package kz.rymbek.platform.common.core.player.playback

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.rymbek.platform.common.core.player.playback.media_session.AppMediaSessionService

class AppMediaController(
    context: Context,
) {
    @UnstableApi
    val sessionToken =
        SessionToken(context, ComponentName(context, AppMediaSessionService::class.java))

    private val controllerFuture = MediaController
        .Builder(context, sessionToken)
        .buildAsync()

    /*private val mediaController: MediaController?
        get() = if (controllerFuture.isDone && !controllerFuture.isCancelled) controllerFuture.get() else null*/

    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player.asStateFlow()

    private val activePlayer: Player? get() = _player.value

    init {
        controllerFuture.addListener(
            {
                _player.value = controllerFuture.get()
            },
            MoreExecutors.directExecutor()
        )
    }

    fun playPause() {
        activePlayer?.apply {
            if (isPlaying) pause() else play()

        }
    }

    fun play() {
        activePlayer?.play()
    }

    fun pause() {
        activePlayer?.pause()
    }

    fun setMediaItems(
        mediaItems: List<MediaItem>,
        startIndex: Int = 0,
        startPositionMs: Long = 0L,
    ) {
        activePlayer?.apply {
            setMediaItems(
                mediaItems,
                startIndex,
                startPositionMs
            )
            prepare()
            play()
        }
    }
}