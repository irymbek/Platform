package kz.rymbek.platform.common.core.design.compound.components.top_app_bar.collapsing

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.AppTopAppBarNavigation
import kz.rymbek.platform.common.core.design.foundation.components.image.AppAsyncImage
import kz.rymbek.platform.common.core.design.foundation.components.spacer.AppSpacer

data class CollapsingToolbarState(
    val scrollState: ScrollState,
    val headerHeightDp: Dp,
    val headerHeightPx: Float
)

@Composable
fun AppCollapsingToolbar(
    modifier: Modifier = Modifier,
    imageUrl: String,
    titleText: String = "",
    onBackClick: () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val scrollState = rememberScrollState()

    val headerHeightDp = remember(configuration) {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mutableStateOf((configuration.screenWidthDp.dp * 4f / 3f))
        } else {
            mutableStateOf((configuration.screenHeightDp.dp * 9f / 16f))
        }
    }

    val headerHeightPx = with(density) {
        headerHeightDp.value.toPx()
    }

    val state = remember {
        CollapsingToolbarState(scrollState, headerHeightDp.value, headerHeightPx)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Header(state, imageUrl)
        Body(state, content)
        Toolbar(titleText, state, onBackClick, actions)
    }
}

@Composable
private fun Header(
    state: CollapsingToolbarState,
    imageUrl: String
) {
    val translationY = -state.scrollState.value / 2f
    val alpha = 1f - (state.scrollState.value / state.headerHeightPx).coerceIn(0f, 1f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(state.headerHeightDp)
            .graphicsLayer {
                this.translationY = translationY
                this.alpha = alpha
            }
    ) {
        AppAsyncImage(
            modifier = Modifier.fillMaxSize(),
            uri = imageUrl
        )
    }
}

@Composable
private fun Body(
    state: CollapsingToolbarState,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(state.scrollState)
            .fillMaxSize()
    ) {
        AppSpacer(state.headerHeightDp)
        content()
    }
}

@Composable
private fun Toolbar(
    titleText: String,
    state: CollapsingToolbarState,
    onBackClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit
) {
    val toolbarState by rememberUpdatedState(
        if (state.scrollState.value >= state.headerHeightPx) {
            MaterialTheme.colorScheme.surface to titleText
        } else {
            Color.Transparent to ""
        }
    )
    val (backgroundColor, title) = toolbarState

    AppTopAppBarNavigation(
        title = title,
        onNavigationClick = onBackClick,
        backgroundColor = backgroundColor,
        actions = actions
    )
}
