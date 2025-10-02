package kz.rymbek.platform.common.core.design.compound.components.top_app_bar

import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun AppTopAppBarTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    AppText(
        modifier = modifier.basicMarquee(),
        text = text,
        style = MaterialTheme.typography.titleMedium,
    )
}