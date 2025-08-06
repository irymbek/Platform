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
    modifier: Modifier = Modifier,
    label: String = "",
    items: List<T>,
    onItemSelected: (
        index: Int,
        text: T,
    ) -> Unit,
    errorMessage: String? = null,
    initialItem: T? = null,
    selectedItemToString: (T) -> String = { it.toString() },
    selectedItemToImage: (T) -> Any? = { null },
    dialogThreshold: Int = 10,
) {
    val expanded: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }
    //Don't use rememberSaveable, on intent you may get percelable error
    val selectedItem = remember(initialItem) { mutableStateOf(initialItem) }

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
                value = selectedItem.value?.let { selectedItemToString(it) } ?: label,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )

            if (items.size < dialogThreshold) {
                SmallDropDownMenu(
                    expanded = expanded,
                    items = items,
                    selectedItemToString = selectedItemToString,
                    selectedItemToImage = selectedItemToImage,
                    onItemSelected = onSelectItem,
                )
            } else {
                LargeDropDownMenuDialog(
                    items = items,
                    expanded = expanded,
                    selectedItemToString = selectedItemToString,
                    selectedItemToImage = selectedItemToImage,
                    onItemSelected = onSelectItem,
                )
            }
        }
    )
}
