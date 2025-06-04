package kz.rymbek.platform.common.base.model

fun String.toNullableFloat(): Float? {
    return this.replace(',', '.').toFloatOrNull()
}

fun String.toSafeFloat(default: Float = 0f): Float {
    return replace(',', '.')
        .toFloatOrNull() ?: default
}

fun Float?.toSafeString(): String = this?.toString() ?: ""