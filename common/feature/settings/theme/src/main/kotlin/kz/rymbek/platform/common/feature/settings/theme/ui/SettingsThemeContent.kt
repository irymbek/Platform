package kz.rymbek.platform.common.feature.settings.theme.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.base.feature.architecture.IEvent
import kz.rymbek.platform.common.business.model.ui.enums.design.AppThemeBrand
import kz.rymbek.platform.common.business.model.ui.enums.design.ModeConfig
import kz.rymbek.platform.common.business.model.ui.models.AppData
import kz.rymbek.platform.common.core.design.foundation.components.button.segmented.AppSingleChoiceSegmentedButtonRow
import kz.rymbek.platform.common.core.design.foundation.components.card.AppFilledCard
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.icon.AppIcon
import kz.rymbek.platform.common.core.design.foundation.components.list.lazy.row.AppLazyRowItem
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.Dimensions
import kz.rymbek.platform.common.core.design.foundation.icons.AppIcons
import kz.rymbek.platform.common.core.design.foundation.theme.AppTheme
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.event.SettingsThemeEvent
import kz.rymbek.platform.common.feature.settings.theme.viewmodel.state.SettingsThemeUiState

@Composable
internal fun SettingsThemeContent(
    modifier: Modifier,
    uiState: SettingsThemeUiState,
    onEvent: (IEvent) -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    AppColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimensions.defaultPaddingDp),
            //.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Dimensions.defaultPaddingDp),
        content = {
            AppText(
                modifier = Modifier
                    .padding(
                        horizontal = Dimensions.defaultPaddingDp,
                    ),
                text = "Тема",
                style = MaterialTheme.typography.titleMedium,
            )
            //ThemeItem()

            AppSingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .padding(
                        horizontal = Dimensions.defaultPaddingDp,
                    ),
                items = ModeConfig.entries,
                selectedIndex = uiState.appData.modeConfig.ordinal,
                onItemSelected = { item ->
                    onEvent(SettingsThemeEvent.Action.UpdateModeConfig(modeConfig = item))
                }
            )

            AppLazyRowItem(
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(Dimensions.defaultPaddingDp),
                items = uiState.appThemeBrand,
                contentPadding = PaddingValues(horizontal = Dimensions.defaultPaddingDp),
                keySelector = { _, item ->
                    item.title
                },
                content = { index, appThemeBrand ->
                    ThemePreviewCard(
                        appThemeBrand = appThemeBrand,
                        modeConfig = uiState.appData.modeConfig,
                        isSelected = uiState.appData.appThemeBrand == appThemeBrand,
                        onClick = {
                            onEvent(SettingsThemeEvent.Action.UpdateThemeBrand(appThemeBrand = appThemeBrand))

                            coroutineScope.launch {
                                listState.animateScrollToItem(index)
                            }
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun ThemePreviewCard(
    appThemeBrand: AppThemeBrand,
    isSelected: Boolean,
    onClick: () -> Unit,
    modeConfig: ModeConfig
) {
    val windowWidthDp = with(LocalDensity.current) {
        LocalWindowInfo.current.containerSize.width.toDp()
    }

    AppTheme(
        appThemeBrand = appThemeBrand,
        modeConfig = modeConfig,
        content = {
            AppColumn(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    AppFilledCard(
                        modifier = Modifier
                            .width(windowWidthDp / 2.5F)
                            .aspectRatio(9f / 16f)
                            .border(
                                width = if (isSelected) 4.dp else 0.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        onClick = onClick,
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        content = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                val primary = MaterialTheme.colorScheme.primary
                                val secondary = MaterialTheme.colorScheme.secondary
                                val tertiary = MaterialTheme.colorScheme.tertiary
                                val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
                                val surfaceContainer = MaterialTheme.colorScheme.surfaceContainer
                                val inversePrimary = MaterialTheme.colorScheme.inversePrimary

                                Canvas(modifier = Modifier.matchParentSize()) {
                                    val width = size.width
                                    val height = size.height

                                    val cardHeight = height * 0.2f
                                    val cornerRadius = 12.dp.toPx()
                                    val squareSize = height * 0.07f // Размер квадрата

                                    // Карточки списка
                                    repeat(4) { i ->
                                        val topOffset = height * 0.03f * (i + 1) + cardHeight * i

                                        drawRoundRect(
                                            color = surfaceVariant,
                                            topLeft = Offset(width * 0.1f, topOffset),
                                            size = Size(width * 0.8f, cardHeight),
                                            cornerRadius = CornerRadius(cornerRadius)
                                        )

                                        // Элементы внутри только во 2-й карточке (индекс 1)
                                        if (i == 1) {
                                            // Квадрат (inversePrimary)
                                            drawRoundRect(
                                                color = inversePrimary,
                                                topLeft = Offset(width * 0.12f, topOffset + cardHeight * 0.15f),
                                                size = Size(squareSize, squareSize * 1.8F),
                                                cornerRadius = CornerRadius(4.dp.toPx())
                                            )

                                            // Два прямоугольника
                                            val contentStartX = width * 0.12f + squareSize + width * 0.02f
                                            val contentWidth = width * 0.6f
                                            val rectCornerRadius = CornerRadius(6.dp.toPx())

                                            drawRoundRect(
                                                color = tertiary,
                                                topLeft = Offset(contentStartX, topOffset + cardHeight * 0.15f),
                                                size = Size(contentWidth, cardHeight * 0.25f),
                                                cornerRadius = rectCornerRadius
                                            )

                                            drawRoundRect(
                                                color = secondary,
                                                topLeft = Offset(contentStartX, topOffset + cardHeight * 0.55f),
                                                size = Size(contentWidth, cardHeight * 0.25f),
                                                cornerRadius = rectCornerRadius
                                            )
                                        }
                                    }

                                    // BottomBar
                                    drawRect(
                                        color = surfaceContainer,
                                        topLeft = Offset(0f, height * 0.85f),
                                        size = Size(width, height * 0.15f)
                                    )

                                    // Bottom Bar элементы (квадраты с закруглёнными углами)
                                    val iconSize = height * 0.06f
                                    repeat(3) { i ->
                                        drawRoundRect(
                                            color = primary,
                                            topLeft = Offset(width * (0.15f + i * 0.3f), height * 0.89f),
                                            size = Size(iconSize, iconSize),
                                            cornerRadius = CornerRadius(4.dp.toPx())
                                        )
                                    }

                                    // FAB (квадрат с закруглёнными углами)
                                    drawRoundRect(
                                        color = primary,
                                        topLeft = Offset(width * 0.75f, height * 0.72f),
                                        size = Size(48F, 48F),
                                        cornerRadius = CornerRadius(4.dp.toPx())
                                    )
                                }


                                // Галочка, если элемент выбран
                                if (isSelected) {
                                    AppIcon(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(6.dp),
                                        imageVector = AppIcons.FilledCheckCircle,
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                        }
                    )

                    AppText(
                        modifier = Modifier.padding(top = 4.dp),
                        text = appThemeBrand.title,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                    )
                }
            )
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewSettingsThemeContent() {
    AppTheme {
        SettingsThemeContent(
            modifier = Modifier.fillMaxSize(),
            uiState = SettingsThemeUiState(
                appData = AppData(
                    modeConfig = ModeConfig.FOLLOW_SYSTEM,
                    appThemeBrand = AppThemeBrand.DEFAULT,
                ),
                appThemeBrand = AppThemeBrand.entries
            ),
            onEvent = {}
        )
    }
}