package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.buttons.PlaybackSpeedToggleButton
import kz.rymbek.platform.common.core.player.ui.base.Constants

@Composable
fun AppPlaybackSpeedToggleButton(
    player: Player?,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.textButtonColors().copy(
        contentColor = Constants.primary
    ),
    interactionSource: MutableInteractionSource? = null,
    speedSelection: List<Float> = listOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 1.75f, 2.0f),
) {
    PlaybackSpeedToggleButton(
        player = player,
        modifier = modifier,
        colors = colors,
        interactionSource = interactionSource,
        speedSelection = speedSelection,
    )
}