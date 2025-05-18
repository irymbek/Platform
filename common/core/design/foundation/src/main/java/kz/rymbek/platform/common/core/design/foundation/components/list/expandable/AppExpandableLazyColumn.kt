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
import kz.rymbek.platform.common.core.architecture.interfaces.Expandable
import kz.rymbek.platform.common.core.architecture.interfaces.Identifiable
import kz.rymbek.platform.common.core.design.foundation.components.list.lazy.column.AppLazyColumn

@Composable
fun <CHILD : Identifiable, PARENT> AppExpandableLazyColumn(
    items: List<PARENT>,
    modifier: Modifier = Modifier,
    content: @Composable (index: Int, item: CHILD) -> Unit,
) where PARENT : Expandable<CHILD>, PARENT : Identifiable {

    // Сохраняем состояние свёрнутости по ID
    val expandedState = remember {
        mutableStateMapOf<Any, Boolean>()
    }

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
            items.forEachIndexed { index, item ->
                val isExpanded = expandedState[item.id] == true

                item(
                    key = "parent_${item.id}",
                    content = {
                        ExpandableHeader(
                            title = item.title,
                            collapsed = isExpanded,
                            onClick = {
                                expandedState[item.id] = !isExpanded
                            }
                        )
                    }
                )

                if (isExpanded) {
                    items(
                        count = item.items.size,
                        key = { childIndex -> "child_${item.items[childIndex].id}" },
                        itemContent = { childIndex ->
                            content(index, item.items[childIndex])
                        }
                    )
                }
            }
        }
    )
}
