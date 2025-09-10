package kz.rymbek.platform.common.core.design.foundation.components.list.expandable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.list.lazy.column.AppLazyColumn

@Composable
fun <PARENT, CHILD> AppExpandableLazyColumn(
    parentList: List<PARENT>,
    childList: (PARENT) -> List<CHILD>,
    modifier: Modifier = Modifier,
    parentKey: (PARENT) -> Long,
    childKey: (CHILD) -> Any,
    content: @Composable (parentIndex: Int, child: CHILD) -> Unit,
    header: @Composable (parent: PARENT, isExpanded: Boolean, onClick: () -> Unit) -> Unit
) {
    val expandedIds = rememberSaveable { mutableStateListOf<Long>() }

    AppLazyColumn(
        modifier = modifier
            .fillMaxSize()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        verticalArrangement = Arrangement.Top,
        content = {
            parentList.forEachIndexed { index, parent ->
                val parentId = parentKey(parent)
                val isExpanded = expandedIds.contains(parentId)

                item(key = "parent_$parentId") {
                    header(parent, isExpanded) {
                        if (isExpanded) expandedIds.remove(parentId)
                        else expandedIds.add(parentId)
                    }
                }

                if (isExpanded) {
                    val children = childList(parent)
                    items(
                        items = children,
                        key = { "child_${childKey(it)}" },
                        itemContent = { content(index, it) }
                    )
                }
            }
        }
    )
}
