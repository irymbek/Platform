package kz.rymbek.platform.common.business.converter.converters

import kotlinx.datetime.Instant
import kz.rymbek.platform.common.base.converter.BaseConverter

class InstantConverter: BaseConverter<Instant>(
    serializer = Instant.serializer()
)