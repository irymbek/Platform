package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.buttons.SeekForwardButton
import androidx.media3.ui.compose.state.SeekForwardButtonState
import kz.rymbek.platform.common.core.player.ui.base.Constants

@Composable
fun AppSeekForwardButton(
    player: Player?,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    tint: Color = Constants.primary,
    onClick: SeekForwardButtonState.() -> Unit = SeekForwardButtonState::onClick,
) {
    SeekForwardButton(
        player = player,
        modifier = modifier,
        colors = colors,
        tint = tint,
        onClick = onClick
    )
}

@Composable
fun AppSeekForwardButton(
    player: Player?,
    modifier: Modifier = Modifier,
    imageVector: SeekForwardButtonState.() -> ImageVector,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    tint: Color = Constants.primary,
    onClick: SeekForwardButtonState.() -> Unit = SeekForwardButtonState::onClick,
) {
    SeekForwardButton(
        player = player,
        modifier = modifier,
        imageVector = imageVector,
        colors = colors,
        tint = tint,
        onClick = onClick
    )
}
