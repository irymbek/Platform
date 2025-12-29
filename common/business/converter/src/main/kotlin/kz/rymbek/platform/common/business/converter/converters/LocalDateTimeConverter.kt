package kz.rymbek.platform.common.business.converter.converters

import kotlinx.datetime.LocalDateTime
import kz.rymbek.platform.common.base.converter.BaseConverter

class LocalDateTimeConverter : BaseConverter<LocalDateTime>(
    serializer = LocalDateTime.serializer()
)