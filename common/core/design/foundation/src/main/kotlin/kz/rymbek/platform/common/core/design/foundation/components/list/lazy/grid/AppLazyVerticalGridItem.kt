package kz.rymbek.platform.common.core.design.foundation.components.list.lazy.grid

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun <T: Any> AppLazyVerticalGridItem(
    modifier: Modifier = Modifier,
    columns: GridCells = GridCells.Fixed(3),
    state: LazyGridState = rememberLazyGridState(),
    contentPadding: PaddingValues = PaddingValues(Dimensions.elementPaddingDp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(Dimensions.elementPaddingDp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(Dimensions.elementPaddingDp),
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    keySelector: ((Int, T) -> Any)?,
    items: List<T>,
    content: @Composable (LazyGridItemScope.(index: Int, item: T) -> Unit),
) {
    AppLazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
        content = {
            itemsIndexed(
                items = items,
                key = keySelector,
            ) { index, item ->
                content(index, item)
            }
        },
    )
}