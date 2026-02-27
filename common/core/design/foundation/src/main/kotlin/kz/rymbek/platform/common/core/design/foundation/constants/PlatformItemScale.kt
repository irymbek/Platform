package kz.rymbek.platform.common.core.design.foundation.constants

object PlatformItemScale {
    /** 20-25% — Совсем мелкие элементы (аватарки авторов, мелкие теги) */
    const val MICRO = 0.2f
    const val TINY = 0.25f

    /** 30% — Очень компактные постеры (когда нужно уместить побольше в ряд) */
    const val COMPACT = 0.3f

    /** 35-40% — Золотой стандарт для постеров (2:3) */
    const val NARROW = 0.35f
    const val STANDARD = 0.4f

    /** 45-55% — Квадраты или крупные вертикальные карточки */
    const val MEDIUM = 0.5f

    /** 60-75% — Горизонтальный контент (видео, эпизоды с описанием) */
    const val WIDE = 0.75f

    /** 85-95% — Акцентные баннеры */
    const val BANNER = 0.9f
}