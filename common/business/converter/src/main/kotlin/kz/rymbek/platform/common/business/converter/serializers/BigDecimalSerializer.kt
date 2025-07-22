package kz.rymbek.platform.common.business.converter.serializers

import kotlinx.serialization.KSerializer
import kz.rymbek.platform.common.base.converter.StringFormatSerializer
import java.math.BigDecimal

object BigDecimalSerializer : KSerializer<BigDecimal> by StringFormatSerializer(
    serialName = "BigDecimal",
    toString = { it.toPlainString() },
    fromString = { BigDecimal(it) }
)
