package kz.rymbek.platform.common.core.design.compound.components.loading

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import kz.rymbek.platform.common.core.design.foundation.components.card.AppFilledCard
import kz.rymbek.platform.common.core.design.foundation.components.dialog.AppDialog
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppCircularProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Preview(showBackground = true)
@Composable
fun LoadingDialogPreview() {
    LoadingDialog(message = "Идёт синхронизация...")
}

@Composable
fun LoadingDialog(
    message: String? = null
) {
    val isDialogOpen = remember { mutableStateOf(true) }
    LaunchedEffect(message) {
        println("LoadingDialog message: $message")
    }
    AppDialog(
        isDialogOpen = isDialogOpen,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        content = {
            AppFilledCard(
                content = {
                    Column(
                        modifier = Modifier
                            .padding(PlatformPaddings.content),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppCircularProgressIndicator()
                        if (!message.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(12.dp))
                            AppText(
                                text = message,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            )
        }
    )
}