package kz.rymbek.platform.common.business.converter.serializers

import kotlinx.serialization.KSerializer
import kz.rymbek.platform.common.base.converter.StringFormatSerializer
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object DurationStringSerializer : KSerializer<Duration> by StringFormatSerializer(
    serialName = "Duration",
    toString = { it.toIsoString() },
    fromString = { it.toLong().toDuration(DurationUnit.SECONDS) }
)
