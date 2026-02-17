package kz.rymbek.platform.common.core.design.compound.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.chip.filter.AppFilterChip
import kz.rymbek.platform.common.core.design.foundation.components.container.AppFlowRow
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons

@Composable
fun <T> FilterSection(
    title: String,
    items: Iterable<T>,
    selectedItems: Iterable<Any>,
    onItemClick: (T) -> Unit,
    labelProvider: (T) -> String,
    codeProvider: (T) -> Any,
    modifier: Modifier = Modifier,
) {
    LabelContainer(
        title = title,
        modifier = modifier,
        content = {
            AppFlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(PlatformPaddings.default),
                content = {
                    items.forEach { item ->
                        val isSelected = codeProvider(item) in selectedItems
                        AppFilterChip(
                            selected = isSelected,
                            onClick = { onItemClick(item) },
                            label = labelProvider(item),
                            leadingIcon = if (isSelected) {
                                {
                                    AppIcon(
                                        imageVector = PlatformIcons.FilledCheck,
                                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                                    )
                                }
                            } else null
                        )
                    }
                }
            )
        }
    )
}