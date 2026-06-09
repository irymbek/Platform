package kz.rymbek.platform.common.base.model.utils

import java.math.BigDecimal

const val HTTPS_PREFIX = "https://"

// Url
fun String?.asImageUrl(host: String) = "$HTTPS_PREFIX$host$this"

// String
fun Any?.toSafeString(default: String = ""): String = this?.toString() ?: default

// Int
fun String.toSafeInt(default: Int = 0): Int =
    trim().toIntOrNull() ?: default

// Long
fun Long?.toSafeLong(default: Long = 0L): Long = this ?: default

// Float
fun String.toNullableFloat(): Float? =
    trim().replace(',', '.').toFloatOrNull()

fun String.toSafeFloat(default: Float = 0f): Float =
    trim().replace(',', '.').toFloatOrNull() ?: default

// Double
fun String.toNullableDouble(): Double? =
    trim().replace(',', '.').toDoubleOrNull()

fun String.toSafeDouble(default: Double = 0.0): Double =
    trim().replace(',', '.').toDoubleOrNull() ?: default

// BigDecimal
fun String.toSafeBigDecimal(default: BigDecimal = BigDecimal.ZERO): BigDecimal =
    trim().toBigDecimalOrNull() ?: default