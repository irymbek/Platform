package kz.rymbek.platform.common.core.file

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import kz.rymbek.platform.common.core.file.Base64Kotlin.decodeToBytes
import org.koin.core.annotation.Single
import java.io.File
import kotlin.uuid.Uuid

@Single
class FileUtils(
    private val context: Context,
) {
    private fun createName() = "${Uuid.random()}"

    fun getFile(path: String): File = File(path)

    fun createFile(
        file: File = context.filesDir
    ): File {
        return File(file, createName())
    }

    fun getFileUri(file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    fun base64ToFile(
        base64: String
    ): File {
        val bytes = decodeToBytes(base64)
        val file = createFile()
        file.writeBytes(bytes)
        return file
    }
}