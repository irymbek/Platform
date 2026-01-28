package kz.rymbek.platform.common.core.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
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
import kz.rymbek.platform.common.core.design.foundation.components.spacer.AppSpacer
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.player.ui.base.button.AppMuteButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppNextButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppPlayPauseButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppPreviousButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppRepeatButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppSeekBackButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppSeekForwardButton
import kz.rymbek.platform.common.core.player.ui.base.button.AppShuffleButton
import kz.rymbek.platform.common.core.player.ui.base.indicator.AppPositionAndDurationText

@Composable
private fun RowControls(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    additionalSpacer: Float? = null,
    buttons: List<@Composable () -> Unit>,
) {
    Row(modifier, horizontalArrangement, verticalAlignment) {
        buttons.forEachIndexed { index, button ->
            button()
            if (index < buttons.lastIndex && additionalSpacer != null) {
                AppSpacer(Modifier.weight(additionalSpacer))
            }
        }
    }
}

@Composable
internal fun BoxScope.Controls(
    player: Player,
) {
    val buttonModifier = Modifier.size(50.dp).background(Color.Gray.copy(alpha = 0.1f), CircleShape)

    RowControls(
        Modifier.fillMaxWidth().align(Alignment.Center),
        buttons =
            listOf(
                { AppPreviousButton(player, buttonModifier) },
                { AppSeekBackButton(player, buttonModifier) },
                {
                    AppPlayPauseButton(
                        player = player,
                        modifier = Modifier
                            .size(PlatformIconSize.md),
                    )
                },
                { AppSeekForwardButton(player, buttonModifier) },
                { AppNextButton(player, buttonModifier) },
            ),
    )

    Column(Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
        HorizontalLinearProgressIndicator(player, Modifier.fillMaxWidth())
        Row(
            modifier =
                Modifier.fillMaxWidth().background(Color.Gray.copy(alpha = 0.4f)).padding(start = 15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AppPositionAndDurationText(player)
            AppSpacer(Modifier.weight(1f))
            PlaybackSpeedPopUpButton(player)
            AppShuffleButton(player)
            AppRepeatButton(player)
            AppMuteButton(player)
        }
    }
}