package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.indicator.DurationText
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppDurationText(
    player: Player,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    DurationText(
        player = player,
        modifier = modifier,
        scope = scope
    )
}