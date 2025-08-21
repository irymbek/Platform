package kz.rymbek.platform.common.core.design.foundation.components.list.expandable

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.base.model.interfaces.Expandable
import kz.rymbek.platform.common.core.design.foundation.components.list.lazy.column.AppLazyColumn

@Composable
fun <PARENT, CHILD> AppExpandableLazyColumn(
    items: List<PARENT>,
    modifier: Modifier = Modifier,
    parentKey: (PARENT) -> Any,
    childKey: (CHILD) -> Any,
    content: @Composable (parentIndex: Int, child: CHILD) -> Unit,
    header: @Composable (parent: PARENT, isExpanded: Boolean, onClick: () -> Unit) -> Unit
) where PARENT : Expandable<CHILD> {

    val expandedState = remember { mutableStateMapOf<Any, Boolean>() }

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
            items.forEachIndexed { index, parent ->
                val parentId = parentKey(parent)
                val isExpanded = expandedState[parentId] == true

                item(
                    key = "parent_$parentId"
                ) {
                    header(
                        parent,
                        isExpanded
                    ) { expandedState[parentId] = !isExpanded }
                }

                if (isExpanded) {
                    items(
                        count = parent.items.size,
                        key = { childIndex -> "child_${childKey(parent.items[childIndex])}" }
                    ) { childIndex ->
                        content(index, parent.items[childIndex])
                    }
                }
            }
        }
    )
}
