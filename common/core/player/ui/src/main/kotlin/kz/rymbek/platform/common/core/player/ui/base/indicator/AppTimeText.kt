package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.indicator.TimeFormat
import androidx.media3.ui.compose.material3.indicator.TimeText
import kotlinx.coroutines.CoroutineScope

@OptIn(UnstableApi::class)
@Composable
fun AppTimeText(
    player: Player,
    modifier: Modifier = Modifier,
    timeFormat: TimeFormat,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    TimeText(
        player = player,
        modifier = modifier,
        timeFormat = timeFormat,
        scope = scope
    )
}