package kz.rymbek.platform.common.core.design.compound.info

import androidx.compose.runtime.Composable
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn

@Composable
fun InfoSection(
    vararg pairs: Pair<String, String>,
) {
    AppColumn(
        content = {
            pairs.forEach { (key, value) ->
                KeyValueRow(key = key, value = value)
            }
        }
    )
}
