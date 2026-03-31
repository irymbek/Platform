package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.indicator.TimeFormat
import androidx.media3.ui.compose.material3.indicator.TimeText
import kotlinx.coroutines.CoroutineScope
import kz.rymbek.platform.common.core.player.ui.base.Constants

@OptIn(UnstableApi::class)
@Composable
fun AppTimeText(
    player: Player?,
    modifier: Modifier = Modifier,
    color: Color = Constants.primary,
    timeFormat: TimeFormat,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    TimeText(
        player = player,
        modifier = modifier,
        color = color,
        timeFormat = timeFormat,
        scope = scope
    )
}
