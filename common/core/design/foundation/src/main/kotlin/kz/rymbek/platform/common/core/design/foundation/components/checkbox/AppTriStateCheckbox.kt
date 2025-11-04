package kz.rymbek.platform.common.core.design.foundation.components.checkbox

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState

@Composable
fun AppTriStateCheckbox(
    state: ToggleableState,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    interactionSource: MutableInteractionSource? = null
) {
    TriStateCheckbox(
        state = state,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
    )
}


@Composable
fun rememberCheckBoxState(
    isChecked: Boolean? = null,
): ToggleableState {
    return remember(
        isChecked
    ) {
        when (isChecked) {
            true -> ToggleableState.On
            false -> ToggleableState.Off
            null -> ToggleableState.Indeterminate
        }
    }
}