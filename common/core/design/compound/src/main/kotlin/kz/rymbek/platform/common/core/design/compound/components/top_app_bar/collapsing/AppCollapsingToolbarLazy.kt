package kz.rymbek.platform.common.core.design.compound.components.top_app_bar.collapsing

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.AppTopAppBarNavigation
import kz.rymbek.platform.common.core.design.foundation.components.image.AppAsyncImage
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppLinearProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.spacer.AppSpacer

data class CollapsingToolbarLazyState(
    val lazyListState: LazyListState,
    val headerHeightDp: Dp,
    val headerHeightPx: Float
)

@Composable
fun AppCollapsingToolbarLazy(
    modifier: Modifier = Modifier,
    imageUrl: String,
    titleText: String = "",
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: LazyListScope.() -> Unit,
    loading: ResultFlow<Unit> = ResultFlow.Initial,
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val lazyListState = rememberLazyListState()

    val headerHeightDp by rememberUpdatedState(
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            configuration.screenWidthDp.dp * 3f / 2f
        } else {
            configuration.screenHeightDp.dp * 9f / 16f
        }
    )

    val headerHeightPx by remember {
        derivedStateOf { with(density) { headerHeightDp.toPx() } }
    }

    val state = remember {
        CollapsingToolbarLazyState(
            lazyListState,
            headerHeightDp,
            headerHeightPx
        )
    }

    Box(modifier.fillMaxSize()) {
        HeaderLazy(state, imageUrl)
        BodyLazy(state, content, loading)
        ToolbarLazy(state, titleText, onBackClick, actions)
    }
}

@Composable
private fun HeaderLazy(
    state: CollapsingToolbarLazyState,
    imageUrl: String,
) {
    val scrollOffset = with(state.lazyListState) {
        if (firstVisibleItemIndex == 0) firstVisibleItemScrollOffset.toFloat() else state.headerHeightPx
    }

    val alpha = 1f - (scrollOffset / state.headerHeightPx).coerceIn(0f, 1f)

    AppAsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(state.headerHeightDp)
            .graphicsLayer {
                translationY = -scrollOffset / 2f
                this.alpha = alpha
            },
        uri = imageUrl
    )
}

@Composable
private fun BodyLazy(
    state: CollapsingToolbarLazyState,
    content: LazyListScope.() -> Unit,
    loading: ResultFlow<Unit>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = state.lazyListState
    ) {
        item { AppSpacer(state.headerHeightDp) }
        if (loading is ResultFlow.Loading) {
            item {
                AppLinearProgressIndicator(Modifier.fillMaxWidth())
            }
        }
        content()
    }
}

@Composable
private fun ToolbarLazy(
    state: CollapsingToolbarLazyState,
    titleText: String,
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit
) {
    val surfaceColor = MaterialTheme.colorScheme.surface

    val (backgroundColor, title) = remember {
        derivedStateOf {
            val isScrolled = state.lazyListState.firstVisibleItemIndex > 0 ||
                    state.lazyListState.firstVisibleItemScrollOffset > state.headerHeightPx
            val bgColor = if (isScrolled) surfaceColor else Color.Transparent
            val toolbarTitle = if (isScrolled) titleText else ""
            bgColor to toolbarTitle
        }
    }.value

    AppTopAppBarNavigation(
        title = title,
        onNavigationClick = onBackClick,
        backgroundColor = backgroundColor,
        actions = actions
    )
}