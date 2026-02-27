package kz.rymbek.platform.common.core.design.foundation.constants

object PlatformItemScale {
    /** 20-25% — Совсем мелкие элементы (аватарки авторов, мелкие теги) */
    val micro = 0.2f
    val tiny = 0.25f

    /** 30% — Очень компактные постеры (когда нужно уместить побольше в ряд) */
    val compact = 0.3f

    /** 35-40% — Золотой стандарт для постеров (2:3) */
    val narrow = 0.35f
    val standard = 0.4f

    /** 45-55% — Квадраты или крупные вертикальные карточки */
    val medium = 0.5f

    /** 60-75% — Горизонтальный контент (видео, эпизоды с описанием) */
    val wide = 0.75f

    /** 85-95% — Акцентные баннеры */
    val banner = 0.9f
}