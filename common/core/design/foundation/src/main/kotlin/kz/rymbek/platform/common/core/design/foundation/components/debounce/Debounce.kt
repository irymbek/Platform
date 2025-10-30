package kz.rymbek.platform.common.core.design.foundation.components.debounce

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.rymbek.platform.common.core.design.foundation.components.progress_indicator.AppCircularProgressIndicator
import kz.rymbek.platform.common.core.design.foundation.constants.PlatformIconSize

/**
 * Modifier для debounce / блокировки повторных кликов.
 *
 * Обратите внимание:
 * - это "throttle" — блокирует повторы на время cooldownMillis после старта onClick.
 * - не рисует спиннер.
 * - есть версия для suspend и для обычного onClick.
 */
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
    val currentOnClick by rememberUpdatedState(onClick)
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
                            currentOnClick()
                        } finally {
                            delay(cooldownMillis)
                            locked = false
                        }
                    }
                }
            )
        )
}

/**
 * suspend-версия: debounce для suspend onClick.
 */
@Composable
fun Modifier.debouncedClickable(
    enabled: Boolean = true,
    cooldownMillis: Long = 1000L,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = null,
    onSuspendClick: suspend () -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    var locked by remember { mutableStateOf(false) }
    val currentOnSuspendClick by rememberUpdatedState(onSuspendClick)
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
                            currentOnSuspendClick()
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
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    loadingOverlay: (@Composable BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.(isLoading: Boolean) -> Unit
) {
    // interaction source для ripple и контроля взаимодействия
    val interactionSource = remember { MutableInteractionSource() }
    val clickableModifier = Modifier
        .then(
            Modifier.clickable(
                enabled = !isLoading,
                onClick = onClick,
                interactionSource = interactionSource,
                indication = LocalIndication.current, // или rememberRipple()
                role = Role.Button
            )
        )

    Box(modifier = modifier.then(clickableModifier)) {
        content(isLoading)

        // Плавное появление оверлея
        AnimatedVisibility(
            modifier = Modifier
                .matchParentSize(),
            visible = isLoading,
            enter = fadeIn(tween(120)),
            exit = fadeOut(tween(120))
        ) {
            // если пользователь передал кастомный оверлей — используем его,
            // иначе — дефолтный скрим + индикатор
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.35f)),
                contentAlignment = Alignment.Center
            ) {
                if (loadingOverlay != null) {
                    // вызываем в BoxScope
                    Box(modifier = Modifier.matchParentSize()) {
                        loadingOverlay()
                    }
                } else {
                    AppCircularProgressIndicator(
                        modifier = Modifier.size(PlatformIconSize.xxs)
                    )
                }
            }
        }
    }
}

/**
 * Универсальная обёртка с overlay-спиннером.
 *
 * Важно: если у child есть свой onClick, лучше убрать его и передать логику сюда,
 * иначе возможна двойная обработка клика.
 *
 * content — BoxScope, чтобы можно было удобно располагать элементы (центрирование и т.д.).
 */
@Composable
fun ClickWithDebounce(
    onClick: suspend () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    cooldownMillis: Long = 1000L,
    overlayAlpha: Float = 0.35f,
    loadingIndicatorSize: Dp = 20.dp,
    content: @Composable BoxScope.(isLoading: Boolean) -> Unit
) {
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val currentOnClick by rememberUpdatedState(onClick)

    Box(modifier = modifier) {
        content(loading)

        if (enabled && !loading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(
                        enabled = true,
                        onClick = {
                            scope.launch {
                                loading = true
                                try {
                                    currentOnClick()
                                } finally {
                                    delay(cooldownMillis)
                                    loading = false
                                }
                            }
                        }
                    )
            )
        }

        if (loading || !enabled) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = overlayAlpha)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(loadingIndicatorSize))
            }
        }
    }
}

@Composable
fun ClickableWithLoading(
    onClick: suspend () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    cooldownMillis: Long = 1000L,
    overlayAlpha: Float = 0.35f,
    loadingIndicatorSize: Dp = 20.dp,

    /**
     * Если передать externalLoading — компонент становится controlled.
     * externalLoading обычно — State<Boolean> из ViewModel (например: viewModel.isLoading.collectAsState()).
     */
    isLoading: Boolean = false,
    /**
     * Callback для изменения состояния загрузки в контролируемом режиме.
     * Если externalLoading != null, рекомендую передавать этот callback.
     */
    onLoadingChanged: ((Boolean) -> Unit)? = null,

    /**
     * Можно заменить стандартный overlay/indicator.
     * Параметр получает текущий флаг isLoading.
     * Если null — используется стандартный затемнённый фон + CircularProgressIndicator.
     */
    loadingOverlay: (@Composable BoxScope.(isLoading: Boolean) -> Unit)? = null,

    content: @Composable BoxScope.(isLoading: Boolean) -> Unit
) {
    var internalLoading by remember { mutableStateOf(isLoading) }
    val scope = rememberCoroutineScope()
    val currentOnClick by rememberUpdatedState(onClick)

    fun setLoading(value: Boolean) {
        onLoadingChanged?.invoke(value)
        internalLoading = value

    }

    Box(modifier = modifier) {
        content(isLoading)

        // overlay clickable area (only clickable when enabled and not loading)
        if (enabled && !isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(
                        enabled = true,
                        onClick = {
                            scope.launch {
                                setLoading(true)
                                try {
                                    currentOnClick()
                                } finally {
                                    // cooldown always handled here (regardless of controlled/uncontrolled)
                                    delay(cooldownMillis)
                                    setLoading(false)
                                }
                            }
                        }
                    )
            )
        }

        if (internalLoading || !enabled) {
            if (loadingOverlay != null) {
                Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                    loadingOverlay(isLoading)
                }
            } else {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = overlayAlpha)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(loadingIndicatorSize))
                }
            }
        }
    }
}
