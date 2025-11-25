package kz.rymbek.platform.common.business.converter.converters

import kotlinx.datetime.LocalDate
import kz.rymbek.platform.common.base.converter.BaseConverter

class LocalDateConverter : BaseConverter<LocalDate>(
    serializer = LocalDate.serializer()
)