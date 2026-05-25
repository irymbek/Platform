import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kz.rymbek.platform.common.core.design.foundation.components.badge.AppBadge
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings

@Composable
fun AppTab(
    selected: Boolean,
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    badgeCount: Int? = null,
    enabled: Boolean = true,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor,
    interactionSource: MutableInteractionSource? = null,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Tab(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            selectedContentColor = selectedContentColor,
            unselectedContentColor = unselectedContentColor,
            interactionSource = interactionSource,
            content = {
                AppText(
                    modifier = Modifier.padding(PlatformPaddings.section),
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        )
        
        if (badgeCount != null && badgeCount > 0) {
            AppBadge(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                content = {
                    AppText(badgeCount.toString())
                }
            )
        }
    }
}