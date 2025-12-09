package kz.rymbek.platform.common.core.design.foundation.components.search_bar

import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun AppSearchBar(
    state: SearchBarState,
    inputField: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = SearchBarDefaults.inputFieldShape,
    colors: SearchBarColors = SearchBarDefaults.colors(),
    tonalElevation: Dp = SearchBarDefaults.TonalElevation,
    shadowElevation: Dp = SearchBarDefaults.ShadowElevation,
) {
    SearchBar(
        state = state,
        inputField = inputField,
        modifier = modifier,
        shape = shape,
        colors = colors,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    )
}