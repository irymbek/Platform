package kz.rymbek.platform.common.feature.settings.theme.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import kz.rymbek.platform.common.base.navigation.core.BaseNavigatorInterface
import kz.rymbek.platform.common.feature.settings.theme.ui.SettingsThemeRoute

@Serializable object SettingsThemeRoute

fun BaseNavigatorInterface.navigateToSettingsTheme() {
    navigate(route = SettingsThemeRoute)
}

fun NavGraphBuilder.settingsThemeScreen() {
    composable<SettingsThemeRoute> {
        SettingsThemeRoute()
    }
}
