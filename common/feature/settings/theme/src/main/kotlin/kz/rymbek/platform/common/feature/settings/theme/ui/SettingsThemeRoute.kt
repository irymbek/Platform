package kz.rymbek.platform.common.feature.settings.theme.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.SettingsThemeViewModel
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.event.SettingsThemeEvent
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SettingsThemeRoute(
    onNavigateBack: () -> Unit,
    viewModel: SettingsThemeViewModel = koinViewModel(),
) {
    viewModel.collectSideEffect {
        when (it) {
            SettingsThemeEvent.Navigation.Back -> onNavigateBack()
        }
    }

    val uiState by viewModel.collectAsState()
    SettingsThemeScreen(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}