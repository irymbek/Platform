package kz.rymbek.platform.common.feature.settings.theme.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.TopAppBarNavigationIcon
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.TopAppBarTitle
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.components.top_app_bar.AppCenterAlignedTopAppBar
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.event.SettingsThemeEvent
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.state.SettingsThemeUiState

@Composable
internal fun SettingsThemeScreen(
    uiState: SettingsThemeUiState,
    onEvent: (IEvent) -> Unit,
) {
    AppScaffold(
        topBar = {
            AppCenterAlignedTopAppBar(
                title = { TopAppBarTitle("Внешний вид") },
                navigationIcon = {
                    TopAppBarNavigationIcon { onEvent(SettingsThemeEvent.Navigation.Back) }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = { scaffoldPadding: PaddingValues ->
            SettingsThemeContent(
                scaffoldPadding = scaffoldPadding,
                uiState = uiState,
                onEvent = onEvent,
            )
        }
    )
}