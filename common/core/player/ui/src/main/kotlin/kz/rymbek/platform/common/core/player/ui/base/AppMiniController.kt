package kz.rymbek.platform.common.core.player.ui.base

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.media3.common.Player
import androidx.media3.common.util.BitmapLoader
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.MiniController

@OptIn(UnstableApi::class)
@Composable
fun AppMiniController(
    player: Player,
    modifier: Modifier = Modifier,
    bitmapLoader: BitmapLoader? = null,
    defaultArtwork: Painter? = null,
    onClick: () -> Unit = {},
) {
    MiniController(
        player = player,
        modifier = modifier,
        bitmapLoader = bitmapLoader,
        defaultArtwork = defaultArtwork,
        onClick = onClick,
    )
}