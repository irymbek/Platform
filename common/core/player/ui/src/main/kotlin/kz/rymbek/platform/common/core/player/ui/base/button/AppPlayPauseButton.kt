package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.buttons.PlayPauseButton
import androidx.media3.ui.compose.state.PlayPauseButtonState
import kz.rymbek.platform.common.core.player.ui.base.Constants

@Composable
fun AppPlayPauseButton(
    player: Player,
    modifier: Modifier = Modifier,
    tint: Color = Constants.primary,
    onClick: PlayPauseButtonState.() -> Unit = PlayPauseButtonState::onClick,
) {
    PlayPauseButton(
        player = player,
        modifier = modifier,
        tint = tint,
        onClick = onClick
    )
}

@Composable
fun AppPlayPauseButton(
    player: Player,
    modifier: Modifier = Modifier,
    imageVector: PlayPauseButtonState.() -> ImageVector,
    tint: Color = Constants.primary,
    onClick: PlayPauseButtonState.() -> Unit = PlayPauseButtonState::onClick,
) {
    PlayPauseButton(
        player = player,
        modifier = modifier,
        imageVector = imageVector,
        tint = tint,
        onClick = onClick
    )
}