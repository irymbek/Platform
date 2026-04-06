package kz.rymbek.platform.common.core.player.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.Player

@Composable
fun AppPlayer(
    player: Player?,
    modifier: Modifier = Modifier
) {
    Player(
        player = player,
        modifier = modifier,
    )
}
