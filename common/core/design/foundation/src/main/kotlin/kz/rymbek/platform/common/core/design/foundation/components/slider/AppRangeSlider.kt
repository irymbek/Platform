package kz.rymbek.platform.common.core.design.foundation.components.slider

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun AppRangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
) {
    RangeSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        colors = colors,
    )
}

@Composable
fun AppRangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    startThumb: @Composable (RangeSliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = startInteractionSource,
            colors = colors,
            enabled = enabled,
        )
    },
    endThumb: @Composable (RangeSliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = endInteractionSource,
            colors = colors,
            enabled = enabled,
        )
    },
    track: @Composable (RangeSliderState) -> Unit = { rangeSliderState ->
        SliderDefaults.Track(
            colors = colors,
            enabled = enabled,
            rangeSliderState = rangeSliderState,
        )
    },
    @IntRange(from = 0) steps: Int = 0,
) {
    RangeSlider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        onValueChangeFinished = onValueChangeFinished,
        colors = colors,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        startThumb = startThumb,
        endThumb = endThumb,
        track = track,
        steps = steps,
    )
}

@Composable
@ExperimentalMaterial3Api
fun AppRangeSlider(
    state: RangeSliderState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    startInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    endInteractionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    startThumb: @Composable (RangeSliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = startInteractionSource,
            colors = colors,
            enabled = enabled,
        )
    },
    endThumb: @Composable (RangeSliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = endInteractionSource,
            colors = colors,
            enabled = enabled,
        )
    },
    track: @Composable (RangeSliderState) -> Unit = { rangeSliderState ->
        SliderDefaults.Track(
            colors = colors,
            enabled = enabled,
            rangeSliderState = rangeSliderState,
        )
    },
) {
    RangeSlider(
        state = state,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        startInteractionSource = startInteractionSource,
        endInteractionSource = endInteractionSource,
        startThumb = startThumb,
        endThumb = endThumb,
        track = track,
    )
}