package kz.rymbek.platform.common.base.feature.architecture

import io.konform.validation.ValidationError

interface ErrorState {
    val errors: List<ValidationError>
}