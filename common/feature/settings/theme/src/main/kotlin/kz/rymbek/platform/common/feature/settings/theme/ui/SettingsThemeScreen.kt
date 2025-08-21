package kz.rymbek.platform.common.feature.settings.theme.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.core.design.compound.components.top_app_bar.center_aligned.AppCenterAlignedTopAppBarTitle
import kz.rymbek.platform.common.core.design.foundation.components.button.icon.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.components.scaffold.AppScaffold
import kz.rymbek.platform.common.core.design.foundation.icons.AppIcons
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.contract.SettingsThemeSideEffect
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.contract.SettingsThemeUiState

@Composable
internal fun SettingsThemeScreen(
    uiState: SettingsThemeUiState,
    onEvent: (IEvent) -> Unit,
) {
    AppScaffold(
        topBar = {
            AppCenterAlignedTopAppBarTitle(
                title = "Внешний вид",
                navigationIcon = {
                    AppIconButton(
                        icon = AppIcons.FilledArrowBackIosNew,
                        onClick = {
                            onEvent(SettingsThemeSideEffect.Navigation.Back)
                        },
                    )
                },
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = { paddingValues: PaddingValues ->
            SettingsThemeContent(
                modifier = Modifier
                    .padding(paddingValues),
                uiState = uiState,
                onEvent = onEvent,
            )
        }
    )
}