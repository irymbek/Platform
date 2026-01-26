package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.buttons.RepeatButton
import androidx.media3.ui.compose.state.RepeatButtonState

@Composable
fun AppRepeatButton(
    player: Player,
    modifier: Modifier = Modifier,
    toggleModeSequence: List<@Player.RepeatMode Int> =
        listOf(Player.REPEAT_MODE_OFF, Player.REPEAT_MODE_ONE, Player.REPEAT_MODE_ALL),
    tint: Color = LocalContentColor.current,
    onClick: RepeatButtonState.() -> Unit = RepeatButtonState::onClick,
) {
    RepeatButton(
        player = player,
        modifier = modifier,
        toggleModeSequence = toggleModeSequence,
        tint = tint,
        onClick = onClick,
    )
}

@Composable
fun AppRepeatButton(
    player: Player,
    modifier: Modifier = Modifier,
    toggleModeSequence: List<@Player.RepeatMode Int> =
        listOf(Player.REPEAT_MODE_OFF, Player.REPEAT_MODE_ONE, Player.REPEAT_MODE_ALL),
    imageVector: RepeatButtonState.() -> ImageVector,
    tint: Color = LocalContentColor.current,
    onClick: RepeatButtonState.() -> Unit = RepeatButtonState::onClick,
) {
    RepeatButton(
        player = player,
        modifier = modifier,
        toggleModeSequence = toggleModeSequence,
        imageVector = imageVector,
        tint = tint,
        onClick = onClick,
    )
}