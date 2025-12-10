package kz.rymbek.platform.common.core.design.foundation.components.search_bar

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.inputFieldColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppSearchBarInputField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = inputFieldColors(),
    interactionSource: MutableInteractionSource? = null
) {
    SearchBarDefaults.InputField(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = onSearch,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier,
        enabled = enabled,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = colors,
        interactionSource = interactionSource
    )
}