package kz.rymbek.platform.common.core.player.ui

import androidx.annotation.IntRange
import androidx.annotation.OptIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.state.rememberProgressStateWithTickCount

@OptIn(UnstableApi::class)
@Composable
fun HorizontalLinearProgressIndicator2(
    player: Player,
    modifier: Modifier = Modifier,
) {
    // Получаем состояние прогресса (текущая позиция и буфер)
    val progressState = rememberProgressStateWithTickCount(player, 0)

    // Флаг, чтобы прогресс ползунка не "прыгал", пока пользователь его тянет
    var isDragging by remember { mutableStateOf(false) }
    var dragProgress by remember { mutableFloatStateOf(0f) }

    val currentProgress = if (isDragging) dragProgress else progressState.currentPositionProgress

    Slider(
        value = currentProgress.coerceIn(0f, 1f),
        onValueChange = {
            isDragging = true
            dragProgress = it
        },
        onValueChangeFinished = {
            // Перематываем плеер по завершении жеста
            val seekPosition = (dragProgress * player.duration).toLong()
            player.seekTo(seekPosition)
            isDragging = false
        },
        modifier = modifier.fillMaxWidth(),
        // Кастомизируем внешний вид под ваш дизайн
        colors = SliderDefaults.colors(
            activeTrackColor = Color.Red,        // Проиграно
            inactiveTrackColor = Color.DarkGray, // Не проиграно
            thumbColor = Color.Red               // Скруббер
        ),
        // Здесь магия: рисуем полоску буфера поверх стандартного трека
        track = { sliderState ->
            SliderDefaults.Track(
                sliderState = sliderState,
                modifier = Modifier.drawBehind {
                    // Рисуем буферную часть
                    val bufferWidth = size.width * progressState.bufferedPositionProgress
                    drawRect(
                        color = Color.LightGray,
                        size = size.copy(width = bufferWidth)
                    )
                },
                colors = SliderDefaults.colors(
                    activeTrackColor = Color.Red,
                    inactiveTrackColor = Color.Transparent // Делаем прозрачным, чтобы видеть буфер
                )
            )
        }
    )
}

@OptIn(UnstableApi::class)
@Composable
fun HorizontalLinearProgressIndicator(
    player: Player,
    modifier: Modifier = Modifier,
    @IntRange(from = 0) totalTickCount: Int = 0,
) {
    var ticks by remember(totalTickCount) { mutableIntStateOf(totalTickCount) }
    val progressState = rememberProgressStateWithTickCount(player, ticks)
    HorizontalLinearProgressIndicator(
        modifier,
        currentPositionProgress = { progressState.currentPositionProgress },
        bufferedPositionProgress = { progressState.bufferedPositionProgress },
        onLayoutWidthChanged = { widthPx -> if (totalTickCount == 0) ticks = widthPx },
    )
}

@Composable
private fun HorizontalLinearProgressIndicator(
    modifier: Modifier = Modifier,
    currentPositionProgress: () -> Float,
    bufferedPositionProgress: () -> Float = currentPositionProgress,
    onLayoutWidthChanged: (Int) -> Unit = {},
    playedColor: Color = Color.Red,
    bufferedColor: Color = Color.LightGray,
    unplayedColor: Color = Color.DarkGray,
    scrubberColor: Color = playedColor,
    scrubberShape: Shape = CircleShape,
    rectHeightDp: Dp = 10.dp,
) {
    // the following X-coordinates are relative to the size of the Canvas, not the whole HLPI
    var positionX by remember { mutableFloatStateOf(0f) }
    var scrubberX by remember { mutableFloatStateOf(0f) }
    var bufferX by remember { mutableFloatStateOf(0f) }
    var barSize by remember { mutableStateOf(Size(0f, 0f)) }
    val rectHeightPx = with(LocalDensity.current) { rectHeightDp.roundToPx() }
    val scrubberBoxSizeDp = 2 * rectHeightDp
    val scrubberBoxSizePx = 2 * rectHeightPx
    val canvasTopDownPaddingDp = 5.dp
    val canvasTopDownPaddingPx = with(LocalDensity.current) { canvasTopDownPaddingDp.roundToPx() }
    val canvasLeftRightPaddingDp = scrubberBoxSizeDp / 2 + canvasTopDownPaddingDp
    val canvasLeftRightPaddingPx = scrubberBoxSizePx / 2 + canvasTopDownPaddingPx

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .background(Color.Gray.copy(alpha = 0.4f))
                .height(scrubberBoxSizeDp + 2 * canvasTopDownPaddingDp),
    ) {
        Canvas(
            Modifier.padding(
                start = canvasLeftRightPaddingDp,
                top = canvasTopDownPaddingDp,
                bottom = canvasTopDownPaddingDp,
                end = canvasLeftRightPaddingDp,
            )
                .fillMaxWidth()
                .height(rectHeightDp)
                .onSizeChanged { (w, _) -> onLayoutWidthChanged(w) }
        ) {
            positionX = (currentPositionProgress() * size.width).coerceAtLeast(0f)
            scrubberX = positionX
            bufferX = (bufferedPositionProgress() * size.width).coerceAtLeast(0f)
            // Canvas.size.height = 0, hence need our own height
            barSize = Size(size.width, rectHeightDp.toPx())

            drawRect(unplayedColor, size = barSize)
            drawRect(bufferedColor, size = barSize.copy(width = bufferX))
            drawRect(playedColor, size = barSize.copy(width = positionX))
        }

        val absoluteThumbX = canvasLeftRightPaddingPx + scrubberX
        val scrubberTopLeftX = absoluteThumbX - scrubberBoxSizePx / 2
        Box(
            Modifier.offset { IntOffset(x = scrubberTopLeftX.toInt(), y = 0) }
                .size(scrubberBoxSizeDp)
                .background(scrubberColor, scrubberShape)
        )
    }
}