package kz.rymbek.platform.common.core.design.compound.components.top_app_bar

import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.icon_button.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons

@Composable
fun AppTopAppBarNavigationIcon(
    onClick: () -> Unit,
) {
    AppIconButton(
        icon = PlatformIcons.FilledArrowBackIosNew,
        onClick = onClick
    )
}
