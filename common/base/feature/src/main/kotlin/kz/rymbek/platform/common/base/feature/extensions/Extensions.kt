package kz.rymbek.platform.common.base.feature.extensions

import io.konform.validation.ValidationError
import io.konform.validation.messagesAtPath
import kotlin.reflect.KProperty1

fun List<ValidationError>.errorAt(path: KProperty1<*, *>): String? =
    messagesAtPath(path).firstOrNull()