package kz.rymbek.platform.common.core.design.compound.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Composable
fun LabelContainer(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PlatformPaddings.default),
        content = {
            AppText(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            content()
        }
    )
}