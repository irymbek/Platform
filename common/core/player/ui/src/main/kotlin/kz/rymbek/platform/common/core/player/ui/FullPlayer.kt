package kz.rymbek.platform.common.core.player.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.media3.common.Player
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.SurfaceType
import androidx.media3.ui.compose.material3.Player
import kotlinx.coroutines.delay
import kz.rymbek.platform.common.core.activity.ActivityUtils
import kz.rymbek.platform.common.core.activity.rememberActivityUtils

@Composable
fun FullPlayer(
    player: Player?,
    modifier: Modifier = Modifier,
    surfaceType: @SurfaceType Int = SURFACE_TYPE_SURFACE_VIEW,
    contentScale: ContentScale = ContentScale.Fit,
    keepContentOnReset: Boolean = false,
    shutter: @Composable () -> Unit = {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
    },
    activityUtils: ActivityUtils = rememberActivityUtils()
) {
    var showControls by remember { mutableStateOf(false) }
    var lastInteraction by remember { mutableLongStateOf(0L) }
    var isPlaying by remember { mutableStateOf(player?.isPlaying == true) }

    BackHandler {
        if (showControls) {
            showControls = false
        } else {
            activityUtils.enterInPipMode()
        }
    }

    activityUtils.EnterInPipAuto()

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
            }
        }
        player?.addListener(listener)
        onDispose { player?.removeListener(listener) }
    }

    LaunchedEffect(lastInteraction, isPlaying) {
        if (isPlaying) {
            delay(3000L)
            showControls = false
        }
    }

    val onInteraction = { lastInteraction = System.currentTimeMillis() }

    Player(
        player = player,
        modifier = modifier
            .background(Color.Black)
            .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            showControls = !showControls
            onInteraction()
        },
        surfaceType = surfaceType,
        contentScale = contentScale,
        keepContentOnReset = keepContentOnReset,
        shutter = shutter,
        showControls = showControls,
        topControls = { _, isVisible ->
            TopContent(
                player = player,
                showControls = isVisible,
                onCloseClick = {
                    showControls = false
                    activityUtils.enterInPipMode()
                },
                onInteraction = onInteraction
            )
        },
        centerControls = { _, isVisible ->
            CenterContent(
                player = player,
                showControls = isVisible,
                onInteraction = onInteraction,
            )
        },
        bottomControls = { _, isVisible ->
            BottomContent(
                player = player,
                showControls = isVisible,
                onInteraction = onInteraction,
            )
        },
    )
}
