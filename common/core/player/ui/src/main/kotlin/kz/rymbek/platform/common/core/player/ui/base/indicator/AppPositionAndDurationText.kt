package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.indicator.PositionAndDurationText
import kotlinx.coroutines.CoroutineScope
import kz.rymbek.platform.common.core.player.ui.base.Constants

@OptIn(UnstableApi::class)
@Composable
fun AppPositionAndDurationText(
    player: Player?,
    modifier: Modifier = Modifier,
    separator: String = " / ",
    color: Color = Constants.primary,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    PositionAndDurationText(
        player = player,
        modifier = modifier,
        separator = separator,
        color = color,
        scope = scope
    )
}
