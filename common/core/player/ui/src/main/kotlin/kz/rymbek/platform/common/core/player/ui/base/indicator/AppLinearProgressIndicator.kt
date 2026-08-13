package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.indicator.LinearProgressIndicator
import kotlinx.coroutines.CoroutineScope

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

@Composable
fun AppLinearProgressIndicator(
    player: Player?,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = rememberCoroutineScope(),
    color: Color = MaterialTheme.colorScheme.primary,
) {
    LinearProgressIndicator(
        player = player,
        modifier = modifier,
        scope = scope,
        color = color,
    )
}