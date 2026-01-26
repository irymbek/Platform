package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.indicator.RemainingDurationText
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppRemainingDurationText(
    player: Player,
    modifier: Modifier = Modifier,
    showNegative: Boolean = false,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    RemainingDurationText(
        player = player,
        modifier = modifier,
        showNegative = showNegative,
        scope = scope
    )
}