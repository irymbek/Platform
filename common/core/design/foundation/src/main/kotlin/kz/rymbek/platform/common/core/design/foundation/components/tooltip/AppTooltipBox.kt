package kz.rymbek.platform.common.core.design.foundation.components.tooltip

import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipScope
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.PopupPositionProvider

@Composable
fun AppTooltipBox(
    positionProvider: PopupPositionProvider,
    tooltip: @Composable TooltipScope.() -> Unit,
    state: TooltipState,
    modifier: Modifier = Modifier,
    onDismissRequest: (() -> Unit)? = null,
    focusable: Boolean = false,
    enableUserInput: Boolean = true,
    hasAction: Boolean = false,
    content: @Composable () -> Unit,
) {
    TooltipBox(
        positionProvider = positionProvider,
        tooltip = tooltip,
        state = state,
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        focusable = focusable,
        enableUserInput = enableUserInput,
        hasAction = hasAction,
        content = content,
    )
}