package kz.rymbek.platform.common.base.model

//Float
fun String.toNullableFloat(): Float? {
    return this.replace(',', '.').toFloatOrNull()
}

fun String.toSafeFloat(default: Float = 0f): Float {
    return replace(',', '.')
        .toFloatOrNull() ?: default
}

fun Any?.toSafeString(
    default: String = "",
): String = this?.toString() ?: default

//Int
fun String.toSafeInt(
    default: Int = 0,
): Int {
    return this.toIntOrNull() ?: default
}

