package kz.rymbek.platform.common.core.design.compound.components.topappbar

import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.iconbutton.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons

@Composable
fun TopAppBarNavigationIcon(
    onClick: () -> Unit,
) {
    AppIconButton(
        icon = PlatformIcons.FilledArrowBackIosNew,
        onClick = onClick
    )
}
