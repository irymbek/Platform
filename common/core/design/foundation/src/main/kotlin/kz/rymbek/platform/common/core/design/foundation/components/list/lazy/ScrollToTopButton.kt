package kz.rymbek.platform.common.core.design.foundation.components.list.lazy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.core.design.foundation.components.icon_button.AppFilledTonalIconButton
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons

@Composable
fun ScrollToTopButton(
    state: LazyGridState,
) {
    val scope = rememberCoroutineScope()

    val isAtTop by remember {
        derivedStateOf {
            state.firstVisibleItemIndex == 0 && state.firstVisibleItemScrollOffset == 0
        }
    }

    AnimatedVisibility(
        visible = !isAtTop,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = PlatformPaddings.default),
            contentAlignment = Alignment.BottomCenter
        ) {
            AppFilledTonalIconButton(
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.size(PlatformIconSize.sm),
                icon = PlatformIcons.FilledKeyboardDoubleArrowUp
            )
        }
    }
}
