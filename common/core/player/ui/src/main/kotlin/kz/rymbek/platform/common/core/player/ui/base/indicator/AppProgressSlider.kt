package kz.rymbek.platform.common.core.player.ui.base.indicator

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.indicator.ProgressSlider
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppProgressSlider(
    player: Player?,
    modifier: Modifier = Modifier
) {
    ProgressSlider(
        player = player,
        modifier = modifier,
    )
}

@Composable
fun AppProgressSlider(
    player: Player?,
    modifier: Modifier = Modifier,
    onValueChange: ((Float) -> Unit)? = null,
    onValueChangeFinished: (() -> Unit)? = null,
    scope: CoroutineScope = rememberCoroutineScope(),
    colors: SliderColors = SliderDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    ProgressSlider(
        player = player,
        modifier = modifier,
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        scope = scope,
        colors = colors,
        interactionSource = interactionSource
    )
}