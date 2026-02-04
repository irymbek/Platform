package kz.rymbek.platform.common.core.file

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.io.encoding.Base64
import kotlin.uuid.Uuid

object Base64Kotlin {
    /**
     * Декодирует строку base64 (поддерживает data:...;base64, префикс).
     * По умолчанию при ошибке бросает IllegalArgumentException — удобно для тестов.
     * Если нужно мягкое поведение (возврат пустого массива) — установите allowEmptyOnError = true.
     */
    fun decodeToBytes(base64: String, allowEmptyOnError: Boolean = false): ByteArray {
        val cleaned = base64.substringAfter("base64,", base64).trim()
        return try {
            Base64.decode(cleaned)
        } catch (e: IllegalArgumentException) {
            if (allowEmptyOnError) ByteArray(0) else throw e
        }
    }

    /**
     * Декодирует в Bitmap или возвращает null при ошибке/пустом результате.
     */
    fun decodeToBitmap(base64: String): Bitmap? {
        val bytes = decodeToBytes(base64, allowEmptyOnError = true)
        if (bytes.isEmpty()) return null
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    /**
     * Кодирует байты в base64.
     * Если wrapDataUri = true — возвращает data:<mime>;base64,<payload>
     */
    fun encode(
        bytes: ByteArray,
        wrapDataUri: Boolean = false,
        mimeType: String = "image/jpeg"
    ): String {
        val encoded = Base64.encode(bytes)
        return if (wrapDataUri) "data:$mimeType;base64,$encoded" else encoded
    }

    fun base64ToFileInfo(base64: String): Pair<String, ByteArray> {
        val bytes = decodeToBytes(base64)
        val name = "${Uuid.random()}.jpg"
        return name to bytes
    }

    /* Удобные расширения, можно вызывать как Base64Kotlin.toBase64Image(bytes) или String.asDataUri() */
    fun String.asDataUri(mimeType: String = "image/jpeg"): String =
        if (startsWith("data:", ignoreCase = true)) this else "data:$mimeType;base64,$this"

    fun ByteArray.toBase64Image(mimeType: String = "image/jpeg"): String =
        encode(this, wrapDataUri = true, mimeType = mimeType)
}