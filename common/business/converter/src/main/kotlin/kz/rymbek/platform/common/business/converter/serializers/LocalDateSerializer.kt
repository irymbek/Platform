package kz.rymbek.platform.common.business.converter.serializers

import kotlinx.datetime.LocalDate
import kz.rymbek.platform.common.base.converter.StringFormatSerializer

val LocalDateSerializer = StringFormatSerializer(
    serialName = "kotlinx.datetime.LocalDate",
    toString = { it.toString() },
    fromString = { LocalDate.parse(it) }
)