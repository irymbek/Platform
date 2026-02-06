package kz.rymbek.platform.common.core.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.icon_button.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.components.spacer.AppSpacer
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings
import kz.rymbek.platform.common.core.design.foundation.icons.PlatformIcons
import kz.rymbek.platform.common.core.player.ui.base.Constants
import kz.rymbek.platform.common.core.player.ui.base.button.AppMuteButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppNextButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppOrientationButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppPlayPauseButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppPreviousButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppRepeatButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppSeekBackButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppSeekForwardButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppShuffleButton
import kz.rymbek.platform.common.core.player.ui.base.indicator.AppPositionAndDurationText

@Composable
private fun TopContent(
    modifier: Modifier = Modifier,
) {
    AppRow(
        modifier = modifier,
        content = {
            AppIconButton(
                icon = PlatformIcons.FilledKeyboardArrowDown,
                tintIcon = Constants.color,
                onClick = {
                    /*BackHandler(enabled = true, onBack = {
                        backPressedCount += 1
                    })*/
                }
            )
        }
    )
}

@Composable
private fun CenterContent(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    buttons: List<@Composable () -> Unit>,
) {
    AppRow (
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = {
            buttons.forEachIndexed { index, button ->
                button()
                if (index < buttons.lastIndex) {
                    AppSpacer(
                        modifier = Modifier
                            .size(PlatformPaddings.content)
                    )
                }
            }
        }
    )
}

@Composable
private fun BottomContent(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    AppColumn(
        modifier = modifier,
        content = content
    )
}

@Composable
internal fun BoxScope.Controls(
    player: Player,
) {
    val buttonModifier = Modifier
        .size(50.dp)
        .background(Color.Black.copy(alpha = 0.3f), CircleShape)

    TopContent(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter),
    )

    CenterContent(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center),
        buttons =
            listOf(
                { AppPreviousButton(player, buttonModifier) },
                { AppSeekBackButton(player, buttonModifier) },
                {
                    AppPlayPauseButton(
                        player = player,
                        modifier = Modifier
                            .size(PlatformIconSize.md)
                            .background(Color.Black.copy(alpha = 0.3f), CircleShape),
                    )
                },
                { AppSeekForwardButton(player, buttonModifier) },
                { AppNextButton(player, buttonModifier) },
            ),
    )

    BottomContent(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        content = {
            AppRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = PlatformPaddings.default),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    AppPositionAndDurationText(
                        player = player
                    )

                    AppOrientationButton()
                }
            )

            HorizontalLinearProgressIndicator2(
                player = player,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            //HorizontalLinearProgressIndicator(player, Modifier.fillMaxWidth())
            Row(
                modifier =
                    Modifier.fillMaxWidth().background(Color.Gray.copy(alpha = 0.4f)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlaybackSpeedPopUpButton(player)
                AppShuffleButton(player)
                AppRepeatButton(player)
                AppMuteButton(player)
            }
        }
    )
}