package kz.rymbek.platform.common.core.design.foundation.constants

object PlatformAspectRatio {
    const val SQUARE = 1f                    // 1:1 (Аватарки, плитка в сетке)

    // --- Горизонтальные (Landscape) ---
    const val LANDSCAPE_STANDARD = 4f / 3f   // 4:3 (Классические фото/планшеты)
    const val LANDSCAPE_PHOTO = 3f / 2f      // 3:2 (Профессиональные фото)
    const val LANDSCAPE_VIDEO = 16f / 9f     // 16:9 (HD видео, YouTube)
    const val LANDSCAPE_WIDE = 2f / 1f       // 2:1 (Современные статьи, новости)
    const val LANDSCAPE_ULTRAWIDE = 21f / 9f // 21:9 (Кинематографичный баннер)
    const val LANDSCAPE_PANORAMIC = 3f / 1f  // 3:1 (Тонкие промо-плашки)

    const val PORTRAIT_CARD = 4f / 5f        // 4:5 (Идеально для ленты новостей, "Instagram-style")
    const val PORTRAIT_STANDARD = 3f / 4f    // 3:4 (Классический вертикальный контент)
    const val PORTRAIT_POSTER = 2f / 3f      // 2:3 (Обложки фильмов, книг, тайтлов)
    const val PORTRAIT_STORY = 9f / 16f      // 9:16 (Stories, Shorts, вертикальные видео)

    const val GOLDEN_RATIO = 1.618f          // 1.618:1 (Для эстетически выверенных макетов)
}