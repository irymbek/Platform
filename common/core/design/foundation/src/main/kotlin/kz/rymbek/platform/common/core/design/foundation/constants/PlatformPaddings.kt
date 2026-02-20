package kz.rymbek.platform.common.core.design.foundation.constants

import androidx.compose.ui.unit.Dp

object PlatformPaddings {
    val element: Dp = PlatformSpacing.space_1x      // 4dp: Микро-отступы (иконка + текст)
    val default: Dp = PlatformSpacing.space_2x      // 8dp: Стандарт между элементами в списке

    // --- НОВЫЕ ШАГИ ---
    val section: Dp = PlatformSpacing.space_4x      // 16dp: Классический "Standard Margin" в Android
    // ------------------

    val content: Dp = PlatformSpacing.space_6x      // 24dp: Отступы для крупных блоков текста
    val large: Dp = PlatformSpacing.space_8x        // 32dp: Разделители между логическими секциями
    val screen: Dp = PlatformSpacing.space_12x      // 48dp: Большие экранные поля
}