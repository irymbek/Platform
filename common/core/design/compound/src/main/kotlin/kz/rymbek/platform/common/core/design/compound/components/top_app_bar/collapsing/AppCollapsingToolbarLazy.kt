package kz.rymbek.platform.common.core.design.compound.components.top_app_bar.collapsing

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.architecture.ResultFlow
import kz.rymbek.platform.common.core.architecture.isLoading
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.AppTopAppBarNavigationIcon
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.AppTopAppBarTitle
import kz.rymbek.platform.common.core.design.foundation.components.container.AppBox
import kz.rymbek.platform.common.core.design.foundation.components.image.AppAsyncImage
import kz.rymbek.platform.common.core.design.foundation.components.list.lazy.column.AppLazyColumn
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppLinearProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.spacer.AppSpacer
import kz.rymbek.platform.common.core.design.foundation.components.top_app_bar.AppTopAppBar

@Composable
private fun rememberHeaderSize(
    portraitRatio: Float,
    landscapeRatio: Float
): Pair<Dp, Float> {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    return remember(windowInfo.containerSize, portraitRatio, landscapeRatio, density) {
        val (wPx, hPx) = windowInfo.containerSize
        val headerDp = if (wPx == 0 || hPx == 0) {
            0.dp
        } else {
            with(density) {
                if (wPx < hPx) wPx.toDp() * portraitRatio
                else hPx.toDp() * landscapeRatio
            }
        }
        val headerPx = with(density) { headerDp.toPx() }
        headerDp to headerPx
    }
}

@Composable
private fun CollapsingHeader(
    imageUrl: Any?,
    headerHeightDp: Dp,
    headerHeightPx: Float,
    scrollOffset: Float,
    parallaxFraction: Float
) {
    val clamped = (scrollOffset / headerHeightPx).coerceIn(0f, 1f)
    val alpha = 1f - clamped
    val translationY = -scrollOffset * parallaxFraction

    AppAsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeightDp)
            .graphicsLayer {
                this.translationY = translationY
                this.alpha = alpha
            },
        uri = imageUrl
    )
}

@Composable
private fun CollapsingToolbar(
    titleText: String,
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit,
    isScrolled: Boolean,
    animateToolbar: Boolean
) {
    val surface = MaterialTheme.colorScheme.surface
    val target = if (isScrolled) surface else Color.Transparent
    val background = if (animateToolbar) animateColorAsState(target).value else target

    val title by rememberUpdatedState(if (isScrolled) titleText else "")

    AppTopAppBar(
        title = { AppTopAppBarTitle(title) },
        navigationIcon = { AppTopAppBarNavigationIcon(onBackClick) },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = background
        )
    )
}

@Composable
fun AppCollapsingToolbarLazy(
    modifier: Modifier = Modifier,
    imageUrl: Any?,
    titleText: String = "",
    onNavigateBack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: LazyListScope.() -> Unit,
    loading: ResultFlow<Unit> = ResultFlow.Initial,
    parallaxFraction: Float = 0.5f,
    portraitRatio: Float = 3f / 2f,
    landscapeRatio: Float = 9f / 16f,
    animateToolbar: Boolean = true,
    lazyState: LazyListState = rememberLazyListState()
) {
    val (headerHeightDp, headerHeightPx) = rememberHeaderSize(portraitRatio, landscapeRatio)

    val scrollOffset by remember(lazyState, headerHeightPx) {
        derivedStateOf {
            if (lazyState.firstVisibleItemIndex == 0) {
                lazyState.firstVisibleItemScrollOffset.toFloat().coerceAtMost(headerHeightPx)
            } else {
                headerHeightPx
            }
        }
    }

    val isScrolled by remember(lazyState, headerHeightPx) {
        derivedStateOf {
            lazyState.firstVisibleItemIndex > 0 || scrollOffset >= headerHeightPx
        }
    }

    AppBox(
        modifier = modifier
            .fillMaxSize(),
        content = {
            CollapsingHeader(
                imageUrl = imageUrl,
                headerHeightDp = headerHeightDp,
                headerHeightPx = headerHeightPx,
                scrollOffset = scrollOffset,
                parallaxFraction = parallaxFraction
            )

            AppLazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                state = lazyState,
                contentPadding = PaddingValues(0.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                content = {
                    item { AppSpacer(headerHeightDp) }

                    if (loading.isLoading) {
                        item {
                            AppLinearProgressIndicator(Modifier.fillMaxWidth())
                        }
                    }

                    content()
                }
            )

            CollapsingToolbar(
                titleText = titleText,
                onBackClick = onNavigateBack,
                actions = actions,
                isScrolled = isScrolled,
                animateToolbar = animateToolbar
            )
        }
    )
}
