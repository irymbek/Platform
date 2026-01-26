package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.buttons.MuteButton
import androidx.media3.ui.compose.state.MuteButtonState

@UnstableApi
@Composable
fun AppMuteButton(
    player: Player,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    onClick: MuteButtonState.() -> Unit = MuteButtonState::onClick,
) {
    MuteButton(
        player = player,
        modifier = modifier,
        tint = tint,
        onClick = onClick,
    )
}

@Composable
fun AppMuteButton(
    player: Player,
    modifier: Modifier = Modifier,
    imageVector: MuteButtonState.() -> ImageVector,
    tint: Color = LocalContentColor.current,
    onClick: MuteButtonState.() -> Unit = MuteButtonState::onClick,
) {
    MuteButton(
        player = player,
        modifier = modifier,
        imageVector = imageVector,
        tint = tint,
        onClick = onClick,
    )
}