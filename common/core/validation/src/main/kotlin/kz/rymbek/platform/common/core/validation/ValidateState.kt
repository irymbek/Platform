package kz.rymbek.platform.common.core.validation

import kz.rymbek.platform.common.core.validation.annotations.Validation
import kz.rymbek.platform.common.core.validation.util.ValidationResult
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

class ValidateState<T : Any>(private val kClass: KClass<T>) {
    fun validate(instance: T): ValidationResult<T> {
        val errors = mutableMapOf<KProperty1<T, *>, String>()

        kClass.memberProperties.forEach { property ->
            property.findAnnotation<Validation>()?.let { annotation ->
                val value = property.get(instance)
                val propertyName = property.name

                // required
                if (annotation.required && isEmpty(value)) {
                    errors[property] = "Заполните поле $propertyName"
                    return@forEach
                }

                // min/max length
                if (value is String) {
                    if (annotation.minLength > 0 && value.length < annotation.minLength) {
                        errors[property] = "Минимальная длина $propertyName — ${annotation.minLength} символов"
                    }
                    if (annotation.maxLength > 0 && value.length > annotation.maxLength) {
                        errors[property] = "Максимальная длина $propertyName — ${annotation.maxLength} символов"
                    }
                }

                // regex
                if (annotation.regex.isNotEmpty() && value is String) {
                    if (!Regex(annotation.regex).matches(value)) {
                        errors[property] = "Поле $propertyName не соответствует формату"
                    }
                }
            }
        }

        return if (errors.isEmpty()) {
            ValidationResult.Success(instance)
        } else {
            ValidationResult.Error(errors)
        }
    }

    private fun isEmpty(value: Any?): Boolean = when (value) {
        null -> true
        is String -> value.isBlank()
        is Collection<*> -> value.isEmpty()
        is Map<*, *> -> value.isEmpty()
        is Long -> value == 0L || value == -1L
        is Double -> value == 0.0
        else -> false
    }
}