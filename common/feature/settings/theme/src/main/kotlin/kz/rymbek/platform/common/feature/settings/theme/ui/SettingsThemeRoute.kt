package kz.rymbek.platform.common.feature.settings.theme.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.SettingsThemeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsThemeRoute(
    settingsThemeViewModel: SettingsThemeViewModel = koinViewModel(),
) {
    val uiState by settingsThemeViewModel.uiState.collectAsStateWithLifecycle()
    SettingsThemeScreen(
        uiState = uiState,
        onEvent = settingsThemeViewModel::onEvent
    )
}