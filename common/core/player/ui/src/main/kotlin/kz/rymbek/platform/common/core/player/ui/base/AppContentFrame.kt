package kz.rymbek.platform.common.core.player.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.media3.common.Player
import androidx.media3.ui.compose.ContentFrame
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.SurfaceType

@Composable
fun AppContentFrame(
    player: Player?,
    modifier: Modifier = Modifier,
    surfaceType: @SurfaceType Int = SURFACE_TYPE_SURFACE_VIEW,
    contentScale: ContentScale = ContentScale.Fit,
    keepContentOnReset: Boolean = false,
    shutter: @Composable () -> Unit = { Box(Modifier.fillMaxSize().background(Color.Black)) },
) {
    ContentFrame(
        player = player,
        modifier = modifier,
        surfaceType = surfaceType,
        contentScale = contentScale,
        keepContentOnReset = keepContentOnReset,
        shutter = shutter
    )
}