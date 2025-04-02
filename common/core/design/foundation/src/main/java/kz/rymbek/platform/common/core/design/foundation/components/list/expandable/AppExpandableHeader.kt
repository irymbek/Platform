package kz.rymbek.platform.common.core.design.foundation.components.list.expandable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.icons.AppIcons
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun ExpandableHeader(
    title: String,
    collapsed: Boolean,
    onClick: () -> Unit,
) {
    AppRow(
        modifier = Modifier
            .clickable {
                onClick()
            },
        content = {
            AppIcon(
                modifier = Modifier
                    .size(Dimensions.iconMedium),
                imageVector = if (collapsed) {
                    AppIcons.FilledExpandMore
                } else {
                    AppIcons.FilledExpandLess
                },
            )
            AppText(
                text = title,
                modifier = Modifier
                    .padding(
                        top = 15.dp,
                        bottom = 15.dp
                    )
                    .weight(1f),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    )
}