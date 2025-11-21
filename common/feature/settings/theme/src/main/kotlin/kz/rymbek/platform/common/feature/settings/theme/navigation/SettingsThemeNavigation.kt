package kz.rymbek.platform.common.feature.settings.theme.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kz.rymbek.platform.common.feature.settings.theme.ui.SettingsThemeRoute

@Serializable
object SettingsThemeKey : NavKey

fun EntryProviderScope<NavKey>.settingsThemeScreen(
    onNavigateBack: () -> Unit,
) {
    entry<SettingsThemeKey> {
        SettingsThemeRoute(
            onNavigateBack = onNavigateBack,
        )
    }
}
