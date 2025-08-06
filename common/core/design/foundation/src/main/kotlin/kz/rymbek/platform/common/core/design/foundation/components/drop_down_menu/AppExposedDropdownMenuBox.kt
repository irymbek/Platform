package kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.base.model.interfaces.Identifiable
import kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu.large.LargeDropDownMenuDialog
import kz.rymbek.platform.common.core.design.foundation.components.drop_down_menu.small.SmallDropDownMenu
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.components.text_field.regular.AppTextField

@Composable
fun <T: Identifiable> AppExposedDropdownMenuBox(
    label: String,
    items: List<T>,
    onItemSelected: (
        index: Int,
        text: T,
    ) -> Unit,
    modifier: Modifier = Modifier,
    itemLabel: (T) -> String = { it.toString() },
    itemImage: (T) -> Any? = { null },
    errorMessage: String? = null,
) {
    AppExposedDropdownMenuBox(
        label = label,
        items = items,
        itemKeySelector = { it.id },
        itemLabel = itemLabel,
        selectedKey = null,
        onItemSelected = onItemSelected,
        modifier = modifier,
        itemImage = itemImage,
        errorMessage = errorMessage,
    )
}


@Composable
fun <T: Identifiable, K: Any> AppExposedDropdownMenuBox(
    label: String,
    items: List<T>,
    itemKeySelector: (T) -> K,
    itemLabel: (T) -> String,
    selectedKey: K?,
    onItemSelected: (
        index: Int,
        text: T,
    ) -> Unit,
    modifier: Modifier = Modifier,
    itemImage: (T) -> Any? = { null },
    errorMessage: String? = null,
) {
    val expanded: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }
    val selectedItem = remember(selectedKey, items) {
        mutableStateOf(items.firstOrNull { itemKeySelector(it) == selectedKey })
    }

    val onSelectItem: (Int, T) -> Unit = { index: Int, item: T ->
        selectedItem.value = item
        expanded.value = false
        onItemSelected(index, item)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
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
                value = selectedItem.value?.let { itemLabel(it) } ?: label,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )

            if (items.size < 10) {
                SmallDropDownMenu(
                    expanded = expanded,
                    items = items,
                    selectedItemToString = itemLabel,
                    selectedItemToImage = itemImage,
                    onItemSelected = onSelectItem,
                )
            } else {
                LargeDropDownMenuDialog(
                    items = items,
                    expanded = expanded,
                    selectedItemToString = itemLabel,
                    selectedItemToImage = itemImage,
                    onItemSelected = onSelectItem,
                )
            }
        }
    )
}
