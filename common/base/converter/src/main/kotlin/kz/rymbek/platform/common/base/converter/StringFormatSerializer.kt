package kz.rymbek.platform.common.base.converter

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class StringFormatSerializer<T>(
    serialName: String,
    private val toString: (T) -> String,
    private val fromString: (String) -> T
) : KSerializer<T> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(serialName, PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): T {
        return fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeString(toString(value))
    }
}