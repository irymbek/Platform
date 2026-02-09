package kz.rymbek.platform.common.core.player.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kz.rymbek.platform.common.core.design.foundation.components.slider.AppSlider

@Composable
fun PlayerProgressSlider(
    player: Player,
    modifier: Modifier = Modifier,
    onInteraction: () -> Unit = {},
    playedColor: Color = MaterialTheme.colorScheme.primary,
    bufferedColor: Color = Color.LightGray.copy(alpha = 0.5f),
    unplayedColor: Color = Color.DarkGray.copy(alpha = 0.5f),
    thumbColor: Color = MaterialTheme.colorScheme.primary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    var duration by remember { mutableLongStateOf(1L) }
    var bufferedPosition by remember { mutableLongStateOf(0L) }
    var sliderValue by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }

    val animatedThumbSize by animateDpAsState(
        targetValue = if (isDragging) 20.dp else 14.dp,
        label = "thumbSize"
    )

    LaunchedEffect(player) {
        while (isActive) {
            duration = player.duration.coerceAtLeast(1L)
            bufferedPosition = player.bufferedPosition
            if (!isDragging) {
                sliderValue = player.currentPosition.toFloat()
            }
            delay(if (player.isPlaying) 100 else 500)
        }
    }

    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
        AppSlider(
            modifier = modifier,
            value = sliderValue,
            onValueChange = {
                onInteraction()
                isDragging = true
                sliderValue = it
            },
            onValueChangeFinished = {
                player.seekTo(sliderValue.toLong())
                isDragging = false
            },
            valueRange = 0f..duration.toFloat(),
            interactionSource = interactionSource,
            thumb = {
                Box(
                    modifier = Modifier
                        .size(animatedThumbSize)
                        .background(thumbColor, CircleShape)
                )
            },
            track = { sliderState ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp), // Высота области клика
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp) // Визуальная высота трека
                    ) {
                        val width = size.width
                        val height = size.height
                        val radius = height / 2
                        val isRtl = layoutDirection == LayoutDirection.Rtl

                        // 1. Фон
                        drawRoundRect(
                            color = unplayedColor,
                            size = size,
                            cornerRadius = CornerRadius(radius)
                        )

                        // 2. Буфер
                        val bufferWidth = (bufferedPosition.toFloat() / duration) * width
                        drawRoundRect(
                            color = bufferedColor,
                            size = Size(bufferWidth.coerceIn(0f, width), height),
                            cornerRadius = CornerRadius(radius),
                            topLeft = Offset(if (isRtl) width - bufferWidth else 0f, 0f)
                        )

                        // 3. Прогресс
                        val sliderFraction = (sliderState.value / duration.toFloat()).coerceIn(0f, 1f)
                        val progressWidth = sliderFraction * width
                        drawRoundRect(
                            color = playedColor,
                            size = Size(progressWidth, height),
                            cornerRadius = CornerRadius(radius),
                            topLeft = Offset(if (isRtl) width - progressWidth else 0f, 0f)
                        )
                    }
                }
            }
        )
    }
}