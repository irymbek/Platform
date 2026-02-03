package kz.rymbek.platform.common.core.file

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import org.koin.core.annotation.Single
import java.io.File
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Single
class FileUtils(
    private val context: Context,
) {
    @OptIn(ExperimentalUuidApi::class)
    private fun createName() = "${Uuid.random()}"

    fun getFile(path: String): File = File(path)

    fun createFilePath(): String {
        val file = File(context.filesDir, createName())
        return file.absolutePath
    }

    fun getFileUri(filePath: String): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            File(filePath)
        )
    }
}