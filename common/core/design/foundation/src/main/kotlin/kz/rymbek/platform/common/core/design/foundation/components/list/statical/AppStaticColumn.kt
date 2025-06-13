package kz.rymbek.platform.common.core.design.foundation.components.list.statical

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn

@Composable
fun <T> AppStaticColumn(
    modifier: Modifier = Modifier,
    items: List<T>,
    content: @Composable (index: Int, item: T) -> Unit,
) {
    AppColumn(
        modifier = modifier,
        content = {
            items.forEachIndexed { index: Int, item: T ->
                content(index, item)
            }
        }
    )
}