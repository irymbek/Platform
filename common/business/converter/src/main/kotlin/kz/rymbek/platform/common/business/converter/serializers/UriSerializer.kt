package kz.rymbek.platform.common.business.converter.serializers

import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kz.rymbek.platform.common.base.converter.StringFormatSerializer

val UriSerializer = StringFormatSerializer(
    serialName = "Uri",
    toString = { it.toString() },
    fromString = { it.toUri() }
)