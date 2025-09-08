package kz.rymbek.platform.common.base.model.utils

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder

fun ValidationBuilder<Long>.greaterThan(min: Long): Constraint<Long> =
    constrain("Поле не должно быть пустым") { it > min }
