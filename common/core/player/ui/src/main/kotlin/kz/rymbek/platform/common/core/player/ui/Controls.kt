package kz.rymbek.platform.common.core.player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import kz.rymbek.platform.common.core.design.foundation.components.container.AppColumn
import kz.rymbek.platform.common.core.design.foundation.components.container.AppRow
import kz.rymbek.platform.common.core.design.foundation.components.icon_button.AppIconButton
import kz.rymbek.platform.common.core.design.foundation.components.spacer.AppSpacer
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformAlpha
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformPaddings
import kz.rymbek.platform.common.core.design.foundation.constants.corner.PlatformShapes
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
    player: Player,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppRow(
        modifier = modifier,
        content = {
            AppIconButton(
                icon = PlatformIcons.FilledKeyboardArrowDown,
                tintIcon = Constants.primary,
                onClick = onCloseClick
            )

            val mediaMetadata = rememberMediaMetadata(player)

            AppColumn(
                content = {
                    AppText(
                        text = mediaMetadata.displayTitle.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = Constants.primary
                    )

                    AppText(
                        text = "Эпизод ${mediaMetadata.subtitle}. ${mediaMetadata.title}",
                        style = MaterialTheme.typography.labelMedium,
                        color = Constants.primary
                    )
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
    AppRow(
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
    onInteraction: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val buttonModifier = Modifier
        .size(50.dp)
        .background(
            color = Constants.background.copy(alpha = PlatformAlpha.OVERLAY),
            shape = CircleShape
        )

    TopContent(
        player = player,
        onCloseClick = onCloseClick,
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Constants.background.copy(alpha = PlatformAlpha.SECONDARY),
                        Constants.background.copy(alpha = PlatformAlpha.TRANSPARENT)
                    )
                )
            )
            .onInteractionTap(onInteraction)
            .padding(
                top = PlatformPaddings.default,
                start = PlatformPaddings.default,
                end = PlatformPaddings.default
            ),
    )

    CenterContent(
        modifier = Modifier
            .align(Alignment.Center)
            .onInteractionTap(onInteraction),
        buttons =
            listOf(
                { AppPreviousButton(player, buttonModifier) },
                { AppSeekBackButton(player, buttonModifier) },
                {
                    AppPlayPauseButton(
                        player = player,
                        modifier = Modifier
                            .size(PlatformIconSize.md)
                            .background(
                                color = Constants.background.copy(alpha = PlatformAlpha.OVERLAY),
                                shape = CircleShape
                            )
                    )
                },
                { AppSeekForwardButton(player, buttonModifier) },
                { AppNextButton(player, buttonModifier) },
            ),
    )

    BottomContent(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Constants.background.copy(alpha = PlatformAlpha.TRANSPARENT),
                        Constants.background.copy(alpha = PlatformAlpha.SCRIM)
                    )
                )
            )
            .onInteractionTap(onInteraction)
            .padding(
                bottom = PlatformPaddings.default,
                start = PlatformPaddings.default,
                end = PlatformPaddings.default
            ),
        content = {
            AppRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = PlatformPaddings.default),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                AppPositionAndDurationText(player)
                AppOrientationButton()
            }

            PlayerProgressSlider(
                player = player,
                modifier = Modifier.fillMaxWidth(),
                onInteraction = onInteraction
            )

            AppRow(
                content = {
                    AppRow(
                        modifier = Modifier
                            .background(
                                color = Constants.background.copy(alpha = PlatformAlpha.OVERLAY),
                                shape = PlatformShapes.medium
                            )
                            .padding(start = PlatformPaddings.element),
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            PlaybackSpeedPopUpButton(player)
                            AppShuffleButton(player)
                            AppRepeatButton(player)
                            AppMuteButton(player)
                        }
                    )
                }
            )
        }
    )
}

@Composable
fun rememberMediaMetadata(player: Player): MediaMetadata {
    var metadata by remember {
        mutableStateOf(player.mediaMetadata)
    }

    DisposableEffect(player) {
        val listener = object : Player.Listener {
            override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
                metadata = mediaMetadata
            }
        }

        player.addListener(listener)
        onDispose { player.removeListener(listener) }
    }

    return metadata
}

fun Modifier.onInteractionTap(onInteraction: () -> Unit): Modifier = this.pointerInput(Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent(PointerEventPass.Initial)
            if (event.changes.any { it.changedToDown() }) {
                onInteraction()
            }
        }
    }
}