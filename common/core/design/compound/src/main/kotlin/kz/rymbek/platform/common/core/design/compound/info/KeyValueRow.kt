package kz.rymbek.platform.common.core.design.compound.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions

@Composable
fun KeyValueRow(
    key: String,
    value: String,
) {
    AppRow(
        horizontalArrangement = Arrangement.spacedBy(Dimensions.elementPaddingDp),
        content = {
            AppText(
                text = "$key:",
                style = MaterialTheme.typography.titleSmall
            )

            AppText(
                text = value,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}