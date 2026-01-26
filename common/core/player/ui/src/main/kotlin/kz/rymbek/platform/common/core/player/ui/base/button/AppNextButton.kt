package kz.rymbek.platform.common.core.player.ui.base.button

import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.media3.common.Player
import androidx.media3.ui.compose.material3.buttons.NextButton
import androidx.media3.ui.compose.state.NextButtonState

@Composable
fun AppNextButton(
    player: Player,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current,
    onClick: NextButtonState.() -> Unit = NextButtonState::onClick,
) {
    NextButton(
        player = player,
        modifier = modifier,
        tint = tint,
        onClick = onClick
    )
}

@Composable
fun AppNextButton(
    player: Player,
    modifier: Modifier = Modifier,
    imageVector: @Composable NextButtonState.() -> ImageVector,
    tint: Color = LocalContentColor.current,
    onClick: NextButtonState.() -> Unit = NextButtonState::onClick,
) {
    NextButton(
        player = player,
        modifier = modifier,
        imageVector = imageVector,
        tint = tint,
        onClick = onClick
    )
}