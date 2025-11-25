package kz.rymbek.platform.common.core.design.foundation.components.segmented_button

import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun <T : Any> AppSingleChoiceSegmentedButtonRow(
    items: List<T>,
    selectedIndex: Int,
    onItemSelected: (T) -> Unit,
    label: @Composable (T) -> Unit,
    modifier: Modifier = Modifier,
    space: Dp = SegmentedButtonDefaults.BorderWidth,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier,
        space = space,
        content = {
            items.forEachIndexed { index, item ->
                AppSegmentedButton(
                    selected = index == selectedIndex,
                    onClick = { onItemSelected(item) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = items.size,
                    ),
                    label = { label(item) }
                )
            }
        }
    )
}