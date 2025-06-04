package kz.rymbek.platform.common.core.design.foundation.components.button.segmented

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import kz.rymbek.platform.common.base.model.interfaces.Filterable
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun <T: Filterable> AppSingleChoiceSegmentedButtonRow(
    items: Iterable<T>,
    selectedIndex: Int,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    space: Dp = SegmentedButtonDefaults.BorderWidth,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = modifier,
        space = space
    ) {
        items.forEachIndexed { index, item ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = items.count(),
                ),
                onClick = { onItemSelected(item) },
                selected = index == selectedIndex
            ) {
                AppText(
                    text = item.label,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}