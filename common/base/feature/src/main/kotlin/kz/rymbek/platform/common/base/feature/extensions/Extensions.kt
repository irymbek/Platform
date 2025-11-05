package kz.rymbek.platform.common.base.feature.extensions

import io.konform.validation.ValidationError
import io.konform.validation.messagesAtPath
import io.konform.validation.path.ValidationPath
import kotlin.reflect.KProperty1

fun List<ValidationError>.errorAt(path: KProperty1<*, *>): String? =
    messagesAtPath(path).firstOrNull()

fun List<ValidationError>.errorAt(
    parent: KProperty1<*, *>,
    index: Int,
    child: KProperty1<*, *>
): String? =
    this.messagesAtPath(parent, index, child).firstOrNull()

fun List<ValidationError>.errorAt(parent: KProperty1<*, *>, child: KProperty1<*, *>): String? =
    this.messagesAtPath(parent, child).firstOrNull()

fun List<ValidationError>.errorStartsWith(parent: KProperty1<*, *>): String? {
    val prefix = ValidationPath.of(parent).dataPath
    return this.firstOrNull { it.dataPath.startsWith(prefix) }?.message
}