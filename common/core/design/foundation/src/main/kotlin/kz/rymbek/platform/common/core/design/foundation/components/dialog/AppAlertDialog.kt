package kz.rymbek.platform.common.core.design.foundation.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import kz.rymbek.platform.common.core.design.foundation.components.button.AppOutlinedButton
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun AppAlertDialog(
    isDialogOpen : MutableState<Boolean>,
    onConfirmationClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit = {
        isDialogOpen.value = false
    },
    title: String = "",
    confirmButtonText: String = "Подтвердить",
    dismissButtonText: String = "Отменить",
    icon: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    shape: Shape = AlertDialogDefaults.shape,
    containerColor: Color = AlertDialogDefaults.containerColor,
    iconContentColor: Color = AlertDialogDefaults.iconContentColor,
    titleContentColor: Color = AlertDialogDefaults.titleContentColor,
    textContentColor: Color = AlertDialogDefaults.textContentColor,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
    properties: DialogProperties = DialogProperties()
) {
    if (isDialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                isDialogOpen.value = false
            },
            confirmButton = {
                AppOutlinedButton(
                    text = confirmButtonText,
                    onClick = {
                        isDialogOpen.value = false
                        onConfirmationClick()
                    }
                )
            },
            modifier = modifier,
            dismissButton = {
                AppOutlinedButton(
                    text = dismissButtonText,
                    onClick = onDismissClick,
                )
            },
            icon = icon,
            title = {
                AppText(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            text = content,
            shape = shape,
            containerColor = containerColor,
            iconContentColor = iconContentColor,
            titleContentColor = titleContentColor,
            textContentColor = textContentColor,
            tonalElevation = tonalElevation,
            properties = properties,
        )
    }
}