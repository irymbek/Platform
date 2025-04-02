package kz.rymbek.platform.common.core.design.foundation.components.tab_row

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun AppTab(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    index: Int,
    title: String,
    enabled: Boolean = true,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Tab(
        modifier = modifier,
        selected = (index == pagerState.currentPage),
        enabled = enabled,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        interactionSource = interactionSource,
        onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(index)
            }
        },
        content = {
            AppText(
                modifier = Modifier
                    .padding(Dimensions.contentPaddingDp),
                text = title,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    )
}