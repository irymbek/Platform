package kz.rymbek.platform.common.core.validation.util

import kz.rymbek.platform.common.core.validation.ValidateState
import kotlin.reflect.KProperty1

interface ValidationError<T: Any> {
    val errors: Map<KProperty1<T, *>, String>
    val validator: ValidateState<T>
}