package kz.rymbek.platform.common.business.converter.converters

import kotlinx.serialization.builtins.serializer
import kz.rymbek.platform.common.base.converter.BaseConverter
import kotlin.uuid.Uuid

class UuidConverter: BaseConverter<Uuid>(
    serializer = Uuid.serializer()
)