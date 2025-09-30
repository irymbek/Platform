package kz.rymbek.platform.common.core.design.compound.components.loading

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import kz.rymbek.platform.common.core.design.foundation.components.card.AppFilledCard
import kz.rymbek.platform.common.core.design.foundation.components.dialog.AppDialog
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppCircularProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Preview(showBackground = true)
@Composable
fun LoadingDialogPreview() {
    LoadingDialog()
}

@Composable
fun LoadingDialog() {
    val isDialogOpen = remember {
        mutableStateOf(true)
    }
    AppDialog(
        isDialogOpen = isDialogOpen,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        content = {
            AppFilledCard(
                content = {
                    AppCircularProgressIndicator(
                        modifier = Modifier
                            .padding(PlatformPaddings.content),
                    )
                }
            )
        }
    )
}