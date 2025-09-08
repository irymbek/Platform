package kz.rymbek.platform.common.base.model.interfaces

import io.konform.validation.Validation

interface Validatable<MODEL : Any> {
    val validator: Validation<MODEL>
}