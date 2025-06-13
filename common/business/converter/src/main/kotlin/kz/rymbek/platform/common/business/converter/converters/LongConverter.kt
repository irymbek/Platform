package kz.rymbek.platform.common.business.converter.converters

import kotlinx.serialization.builtins.serializer
import kz.rymbek.platform.common.base.converter.BaseConverter

class LongConverter: BaseConverter<Long>(
    serializer = Long.serializer()
)