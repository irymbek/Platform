package kz.rymbek.platform.common.core.design.foundation.components.list.lazy.column

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.architecture.interfaces.Identifiable
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun <T: Identifiable> AppLazyColumnItem(
    items: List<T>,
    content: @Composable (LazyItemScope.(index: Int, item: T) -> Unit),
    modifier: Modifier = Modifier,
    headerContent: LazyListScope.() -> Unit = {},
    endContent: LazyListScope.() -> Unit = {},
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(Dimensions.elementPaddingDp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(Dimensions.elementPaddingDp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
) {
    AppLazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = {
            headerContent()
            itemsIndexed(
                items = items,
                key = { _, item ->
                    item.id
                },
                itemContent = { index, item ->
                    content(index, item)
                }
            )
            endContent()
        },
    )
}