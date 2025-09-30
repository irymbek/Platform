package kz.rymbek.platform.common.core.design.foundation.components.checkbox

import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.container.AppBox
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons
import kz.rymbek.platform.common.core.design.foundation.theme.color.custom.colorSchemeCustom

enum class CheckboxState {
    ERROR, SUCCESS, NEUTRAL
}

@Composable
fun AppTriStateCheckboxCustom(
    state: CheckboxState = rememberCheckBoxState(),
    onLongClick: () -> Unit
) {
    val color = when (state) {
        CheckboxState.ERROR -> MaterialTheme.colorScheme.error
        CheckboxState.SUCCESS -> MaterialTheme.colorSchemeCustom.success
        CheckboxState.NEUTRAL -> MaterialTheme.colorSchemeCustom.neutral
    }

    val imageVector = when (state) {
        CheckboxState.ERROR -> PlatformIcons.FilledClose
        CheckboxState.SUCCESS -> PlatformIcons.FilledCheck
        CheckboxState.NEUTRAL -> PlatformIcons.FilledRemove
    }

    AppBox(
        modifier = Modifier
            .size(PlatformIconSize.lg)
            .border(2.dp, color, shape = RoundedCornerShape(4.dp))
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            ),
        contentAlignment = Alignment.Center,
        content = {
            AppIcon(
                imageVector = imageVector,
                tint = color
            )
        }
    )
}

@Composable
fun rememberCheckBoxState(
    isChecked: Boolean? = null,
): CheckboxState {
    return remember(
        isChecked
    ) {
        when (isChecked) {
            true -> CheckboxState.SUCCESS
            false -> CheckboxState.ERROR
            null -> CheckboxState.NEUTRAL
        }
    }
}