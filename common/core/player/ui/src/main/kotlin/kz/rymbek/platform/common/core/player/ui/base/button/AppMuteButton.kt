package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.annotation.OptIn
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.buttons.MuteButton
import androidx.media3.ui.compose.state.MuteButtonState
import kz.rymbek.platform.common.core.player.ui.base.Constants

@OptIn(UnstableApi::class)
@Composable
fun AppMuteButton(
    player: Player?,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    tint: Color = Color.White,
    onClick: MuteButtonState.() -> Unit = MuteButtonState::onClick,
) {
    MuteButton(
        player = player,
        modifier = modifier,
        colors = colors,
        tint = tint,
        onClick = onClick,
    )
}

@Composable
fun AppMuteButton(
    player: Player?,
    modifier: Modifier = Modifier,
    imageVector: MuteButtonState.() -> ImageVector,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    tint: Color = Constants.primary,
    onClick: MuteButtonState.() -> Unit = MuteButtonState::onClick,
) {
    MuteButton(
        player = player,
        modifier = modifier,
        imageVector = imageVector,
        colors = colors,
        tint = tint,
        onClick = onClick,
    )
}
