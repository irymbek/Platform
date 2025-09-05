package kz.rymbek.platform.common.core.validation.util

import kz.rymbek.platform.common.core.validation.ValidateState
import kotlin.reflect.KProperty1

fun <T : Any> Map<KProperty1<T, *>, String>.errorFor(prop: KProperty1<T, *>): String? =
    this[prop]

fun <T : Any> T.validate(
    validator: ValidateState<T>,
    onSuccess: (T) -> Unit,
    onError: (Map<KProperty1<T, *>, String>) -> Unit
) {
    when (val result = validator.validate(this)) {
        is ValidationResult.Success -> onSuccess(result.data)
        is ValidationResult.Error -> onError(result.errors)
    }
}

class ValidationScope<T : Any>(
    private val result: ValidationResult<T>
) {
    suspend fun onSuccess(block: suspend (T) -> Unit) {
        if (result is ValidationResult.Success) block(result.data)
    }

    suspend fun onError(block: suspend (Map<KProperty1<T, *>, String>) -> Unit) {
        if (result is ValidationResult.Error) block(result.errors)
    }
}

suspend inline fun <T : Any> ValidateState<T>.validate(
    instance: T,
    block: suspend ValidationScope<T>.() -> Unit
) {
    val result = validate(instance)
    ValidationScope(result).block()
}