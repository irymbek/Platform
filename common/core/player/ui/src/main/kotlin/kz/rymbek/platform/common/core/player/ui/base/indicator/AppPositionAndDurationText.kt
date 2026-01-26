package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.indicator.PositionAndDurationText
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppPositionAndDurationText(
    player: Player,
    modifier: Modifier = Modifier,
    separator: String = " / ",
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    PositionAndDurationText(
        player = player,
        modifier = modifier,
        separator = separator,
        scope = scope
    )
}