package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.indicator.DurationText
import kotlinx.coroutines.CoroutineScope

@UnstableApi
@Composable
fun AppDurationText(
    player: Player?,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    DurationText(
        player = player,
        modifier = modifier,
        color = color,
        scope = scope
    )
}
