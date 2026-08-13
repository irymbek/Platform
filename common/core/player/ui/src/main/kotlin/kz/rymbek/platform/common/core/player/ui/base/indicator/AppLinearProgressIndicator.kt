package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.indicator.LinearProgressIndicator

@Composable
fun AppLinearProgressIndicator(
    player: Player?,
    modifier: Modifier = Modifier,
) {
    LinearProgressIndicator(
        player = player,
        modifier = modifier
    )
}