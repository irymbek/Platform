package kz.rymbek.platform.common.base.feature.extensions

import io.konform.validation.ValidationError
import io.konform.validation.messagesAtPath
import kotlin.reflect.KProperty1

fun List<ValidationError>.errorAt(path: KProperty1<*, *>): String? =
    messagesAtPath(path).firstOrNull()

fun List<ValidationError>.errorAt(
    parent: KProperty1<*, *>,
    index: Int,
    child: KProperty1<*, *>
): String? =
    this.messagesAtPath(parent, index, child).firstOrNull()