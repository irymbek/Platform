package kz.rymbek.platform.common.core.design.foundation.components.container

import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.GridConfigurationScope
import androidx.compose.foundation.layout.GridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalGridApi::class)
@Composable
inline fun AppGrid(
    noinline config: GridConfigurationScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable GridScope.() -> Unit,
) {
    Grid(
        config = config,
        modifier = modifier,
        content = content,
    )
}