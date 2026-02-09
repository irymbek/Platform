package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.ui.compose.indicators.TimeText
import kotlinx.coroutines.CoroutineScope
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.corner.PlatformShapes
import java.util.Formatter
import java.util.Locale

@OptIn(UnstableApi::class)
@Composable
fun AppPositionAndDurationText(
    player: Player,
    modifier: Modifier = Modifier,
    separator: String = " / ",
    scope: CoroutineScope = rememberCoroutineScope(),
    color: Color = Color.White
) {
    TimeText(
        player = player,
        scope = scope,
        content = {
            val positionText = remember(currentPositionMs) {
                Util.getStringForTime(StringBuilder(), Formatter(Locale.getDefault()), currentPositionMs)
            }
            val durationText = remember(durationMs) {
                Util.getStringForTime(StringBuilder(), Formatter(Locale.getDefault()), durationMs)
            }

            AppText(
                text = "$positionText$separator$durationText",
                modifier = modifier
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = PlatformShapes.medium
                    )
                    .padding(6.dp),
                color = color,
                style = MaterialTheme.typography.labelMedium
            )
        }
    )
}
