package kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu.large.LargeDropDownMenuDialog
import kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu.small.SmallDropDownMenu
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.components.text_field.regular.AppTextField

@Composable
fun <T: Any> AppExposedDropdownMenuBox(
    label: String,
    items: List<T>,
    itemKey: ((Int, T) -> Any)?,
    onItemSelected: (
        item: T,
    ) -> Unit,
    modifier: Modifier = Modifier,
    itemLabel: (T) -> String = { it.toString() },
    itemImage: (T) -> Any? = { null },
    errorMessage: String? = null,
) {
    AppExposedDropdownMenuBox(
        label = label,
        items = items,
        itemLabel = itemLabel,
        selectedKey = null,
        itemKey = itemKey,
        onItemSelected = onItemSelected,
        modifier = modifier,
        itemImage = itemImage,
        errorMessage = errorMessage,
    )
}

@Composable
fun <T: Any, KEY: Any> AppExposedDropdownMenuBox(
    label: String,
    items: List<T>,
    selectedKey: KEY?,
    itemLabel: (T) -> String,
    itemKey: ((Int, T) -> Any)?,
    onItemSelected: (
        item: T,
    ) -> Unit,
    modifier: Modifier = Modifier,
    itemImage: (T) -> Any? = { null },
    errorMessage: String? = null,
) {
    val expanded = rememberSaveable { mutableStateOf(false) }

    var selectedItem by remember(items, selectedKey) {
        mutableStateOf(
            items.withIndex()
                .firstOrNull { (i, item) -> itemKey?.invoke(i, item) == selectedKey }
                ?.value
        )
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = it
        },
        content = {
            AppTextField(
                modifier = Modifier
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryNotEditable,
                        enabled = true,
                    )
                    .fillMaxWidth(),
                readOnly = true,
                singleLine = true,
                isError = errorMessage != null,
                supportingText = {
                    if (errorMessage != null) {
                        AppText(text = errorMessage, color = MaterialTheme.colorScheme.error)
                    }
                },
                value = selectedItem?.let { itemLabel(it) } ?: label,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )

            when {
                items.isEmpty() -> Unit
                items.size < 10 -> SmallDropDownMenu(
                    expanded = expanded,
                    items = items,
                    selectedItemToString = itemLabel,
                    selectedItemToImage = itemImage,
                    onSelectItem = {
                        expanded.value = false
                        onItemSelected(it)
                    },
                )
                else -> LargeDropDownMenuDialog(
                    items = items,
                    expanded = expanded,
                    selectedItemToString = itemLabel,
                    selectedItemToImage = itemImage,
                    keySelector = itemKey,
                    onSelectItem = {
                        expanded.value = false
                        onItemSelected(it)
                    },
                )
            }
        }
    )
}
