package kz.rymbek.platform.common.base.model

import java.math.BigDecimal

const val HTTPS_PREFIX = "https://"

fun String.asImageUrl(
    host: String
): String {
    return "$HTTPS_PREFIX$host$this"
}

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

/** Safe Int **/
fun String.toSafeInt(
    default: Int = 0,
): Int {
    return this.toIntOrNull() ?: default
}

/** Safe BigDecimal **/
fun String.toSafeBigDecimal(
    default: BigDecimal = BigDecimal.ZERO,
): BigDecimal {
    return this.toBigDecimalOrNull() ?: default
}

