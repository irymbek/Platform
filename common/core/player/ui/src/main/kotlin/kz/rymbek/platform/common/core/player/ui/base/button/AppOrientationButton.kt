package kz.rymbek.platform.common.core.player.ui.base.button

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import kz.rymbek.platform.common.core.activity.rememberActivityUtils
import kz.rymbek.platform.common.core.design.foundation.components.icon_button.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons
import kz.rymbek.platform.common.core.player.ui.base.Constants

@Composable
fun AppOrientationButton(
    modifier: Modifier = Modifier,
    tint: Color = Constants.primary,
) {
    val utils = rememberActivityUtils()
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    AppIconButton(
        modifier = modifier,
        icon = if (isLandscape) PlatformIcons.FilledFullscreenExit else PlatformIcons.FilledFullscreen,
        tintIcon = tint,
        onClick = { utils.toggleOrientation() },
    )
}