package kz.rymbek.platform.common.business.converter.serializers

import androidx.core.net.toUri
import kz.rymbek.platform.common.base.converter.StringFormatSerializer

val UriSerializer = StringFormatSerializer(
    serialName = "Uri",
    toString = { it.toString() },
    fromString = { it.toUri() }
)