package kz.rymbek.platform.common.core.validation.util

import kotlin.reflect.KProperty1

sealed class ValidationResult<T : Any> {
    data class Success<T : Any>(val data: T) : ValidationResult<T>()
    data class Error<T : Any>(
        val errors: Map<KProperty1<T, *>, String>
    ) : ValidationResult<T>()
}