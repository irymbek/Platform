package kz.rymbek.platform.common.business.converter.converters

import kz.rymbek.platform.common.base.converter.BaseConverter
import kz.rymbek.platform.common.business.converter.serializers.BigDecimalSerializer
import java.math.BigDecimal

class BigDecimalConverter: BaseConverter<BigDecimal>(
    serializer = BigDecimalSerializer,
)