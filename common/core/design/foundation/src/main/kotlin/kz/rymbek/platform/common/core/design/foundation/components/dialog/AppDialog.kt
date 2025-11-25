package kz.rymbek.platform.common.core.design.foundation.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun AppDialog(
    isDialogOpen: MutableState<Boolean>,
    properties: DialogProperties = DialogProperties(
        usePlatformDefaultWidth = false
    ),
    content: @Composable () -> Unit
) {
    if (isDialogOpen.value) {
        Dialog(
            onDismissRequest = {
                isDialogOpen.value = false
            },
            properties = properties,
            content = content,
        )
    }
}