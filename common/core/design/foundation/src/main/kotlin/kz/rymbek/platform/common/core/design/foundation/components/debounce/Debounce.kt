package kz.rymbek.platform.common.core.design.foundation.components.debounce

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppCircularProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize

@Composable
fun Modifier.debouncedClickable(
    enabled: Boolean = true,
    cooldownMillis: Long = 1000L,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = null,
    onClick: () -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    var locked by remember { mutableStateOf(false) }
    val isEnabled = enabled && !locked

    this
        .then(
            Modifier.clickable(
                enabled = isEnabled,
                interactionSource = interactionSource,
                indication = indication,
                onClick = {
                    scope.launch {
                        locked = true
                        try {
                            onClick()
                        } finally {
                            delay(cooldownMillis)
                            locked = false
                        }
                    }
                }
            )
        )
}

@Composable
fun Modifier.debouncedClickableSuspend(
    enabled: Boolean = true,
    cooldownMillis: Long = 1000L,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = null,
    onSuspendClick: suspend () -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    var locked by remember { mutableStateOf(false) }
    val isEnabled = enabled && !locked

    this
        .then(
            Modifier.clickable(
                enabled = isEnabled,
                interactionSource = interactionSource,
                indication = indication,
                onClick = {
                    scope.launch {
                        locked = true
                        try {
                            onSuspendClick()
                        } finally {
                            delay(cooldownMillis)
                            locked = false
                        }
                    }
                }
            )
        )
}

@Composable
fun ClickWithLoading(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    loadingOverlay: (@Composable BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.(isLoading: Boolean) -> Unit
) {
    Box(
        modifier = modifier,
        content = {
            content(isLoading)

            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = isLoading,
                enter = fadeIn(tween(120)),
                exit = fadeOut(tween(120))
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.35f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (loadingOverlay != null) {
                        Box(modifier = Modifier.matchParentSize()) {
                            loadingOverlay()
                        }
                    } else {
                        AppCircularProgressIndicator(
                            modifier = Modifier.size(PlatformIconSize.md)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ClickWithDebounce(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cooldownMillis: Long = 1000L,
    loadingOverlay: (@Composable BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .clickable(
                enabled = !isLoading,
                onClick = {
                    scope.launch {
                        isLoading = true
                        try {
                            onClick()
                        } finally {
                            delay(cooldownMillis)
                            isLoading = false
                        }
                    }
                }
            ),
        content = {
            content()

            AnimatedVisibility(
                modifier = Modifier
                    .matchParentSize(),
                visible = isLoading,
                enter = fadeIn(tween(120)),
                exit = fadeOut(tween(120))
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.35f)),
                    contentAlignment = Alignment.Center
                ) {
                    if (loadingOverlay != null) {
                        Box(modifier = Modifier.matchParentSize()) {
                            loadingOverlay()
                        }
                    } else {
                        AppCircularProgressIndicator(
                            modifier = Modifier.size(PlatformIconSize.md)
                        )
                    }
                }
            }
        }
    )
}
