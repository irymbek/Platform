package kz.rymbek.platform.common.core.player.ui.base.indicator.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.media3.common.ErrorMessageProvider
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.material3.text.ErrorText
import androidx.media3.ui.compose.material3.text.rememberDefaultErrorMessageProvider

@UnstableApi
@Composable
fun AppErrorText(
    player: Player?,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    customErrorMessage: CharSequence? = null,
    errorMessageProvider: ErrorMessageProvider<PlaybackException>? =
        rememberDefaultErrorMessageProvider(),
) {
    ErrorText(
        player = player,
        modifier = modifier,
        color = color,
        customErrorMessage = customErrorMessage,
        errorMessageProvider = errorMessageProvider,
    )
}