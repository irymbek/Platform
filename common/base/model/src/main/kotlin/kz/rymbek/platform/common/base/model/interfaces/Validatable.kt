package kz.rymbek.platform.common.base.model.interfaces

import io.konform.validation.Validation

interface Validatable<SELF : Any> {
    val validator: Validation<SELF>
}