package kz.rymbek.platform.common.base.model.interfaces

import io.konform.validation.Validation

interface HasValidator<MODEL : Any> {
    val validator: Validation<MODEL>
}