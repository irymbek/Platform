package kz.rymbek.platform.common.core.design.foundation.components.segmented_button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun SingleChoiceSegmentedButtonRowScope.AppSegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
    contentPadding: PaddingValues = SegmentedButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource? = null,
    icon: @Composable () -> Unit = { SegmentedButtonDefaults.Icon(selected) },
    label: @Composable () -> Unit,
) {
    SegmentedButton(
        selected = selected,
        onClick = onClick,
        shape = shape,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        icon = icon,
        label = label,
    )
}