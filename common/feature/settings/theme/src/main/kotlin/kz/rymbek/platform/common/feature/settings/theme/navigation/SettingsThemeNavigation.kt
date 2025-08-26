package kz.rymbek.platform.common.feature.settings.theme.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import kz.rymbek.platform.common.feature.settings.theme.ui.SettingsThemeRoute

@Serializable
object SettingsThemeRoute

fun NavController.navigateToSettingsTheme(
    navOptions: NavOptions? = null,
) {
    navigate(
        route = SettingsThemeRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.settingsThemeScreen(
    onNavigateBack: () -> Unit,
) {
    composable<SettingsThemeRoute> {
        SettingsThemeRoute(
            onNavigateBack = onNavigateBack,
        )
    }
}
