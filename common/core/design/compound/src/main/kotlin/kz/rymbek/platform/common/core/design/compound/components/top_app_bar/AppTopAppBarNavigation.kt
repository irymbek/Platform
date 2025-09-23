package kz.rymbek.platform.common.core.design.compound.components.top_app_bar

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.button.icon.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.components.top_app_bar.AppTopAppBar
import kz.rymbek.platform.common.core.design.foundation.icons.AppIcons

@Composable
fun AppTopAppBarNavigation(
    modifier: Modifier = Modifier,
    title: String = "",
    backgroundColor: Color = Color.Unspecified,
    onNavigationClick: () -> Unit = {},
    actions: @Composable() (RowScope.() -> Unit) = {},
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    windowInsets: WindowInsets = WindowInsets(0.dp),
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = backgroundColor
    ),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    AppTopAppBar(
        modifier = modifier,
        title = {
            AppText(
                modifier = Modifier
                    .basicMarquee(),
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            AppIconButton(
                icon = AppIcons.FilledArrowBackIosNew,
                onClick = {
                    onNavigationClick()
                },
            )
        },
        actions = actions,
        expandedHeight = expandedHeight,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
    )
}