package kz.rymbek.platform.common.business.converter.serializers

import kz.rymbek.platform.common.base.converter.StringFormatSerializer
import kotlin.time.Instant

val InstantSerializer = StringFormatSerializer(
    serialName = "kotlin.time.Instant",
    toString = { it.toString() },
    fromString = { Instant.parse(it) }
)