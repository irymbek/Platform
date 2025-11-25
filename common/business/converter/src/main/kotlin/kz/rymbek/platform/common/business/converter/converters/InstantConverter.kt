package kz.rymbek.platform.common.business.converter.converters

import kotlinx.serialization.builtins.serializer
import kz.rymbek.platform.common.base.converter.BaseConverter
import kotlin.time.Instant

class InstantConverter : BaseConverter<Instant>(
    serializer = Instant.serializer()
)