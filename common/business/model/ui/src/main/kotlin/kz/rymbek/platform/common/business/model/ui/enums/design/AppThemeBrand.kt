package kz.rymbek.platform.common.business.model.ui.enums.design

import kz.rymbek.platform.common.base.model.interfaces.Identifiable

enum class AppThemeBrand(
    override val id: Long,
    val title: String,
): Identifiable {
    DEFAULT(
        id = 0L,
        title = "Стандартная",
    ),
    DYNAMIC(
        id = 1L,
        title = "Динамическая",
    ),
    GREEN_APPLE(
        id = 2L,
        title = "Зелёное Яблоко",
    ),
    LAVENDER(
        id = 3L,
        title = "Лаванда",
    ),
    MIDNIGHT_DUSK(
        id = 4L,
        title = "Полуночные Сумерки",
    ),
    STRAWBERRY_DAIQUIRI(
        id = 5L,
        title = "Клубничный Дайкири",
    ),
    TAKO(
        id = 6L,
        title = "Тако",
    ),
    TEAL_TURQUOISE(
        id = 7L,
        title = "Бирюзовый",
    ),
    TIDAL_WAVE(
        id = 8L,
        title = "Приливная Волна",
    ),
    YIN_YANG(
        id = 9L,
        title = "Инь и Янь",
    ),
    YOTSUBA(
        id = 10L,
        title = "Ёцуба",
    ),
}