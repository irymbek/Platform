package kz.rymbek.platform.common.core.activity

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kz.uso.common.core.file.FileUtils
import org.koin.compose.koinInject

class GetContentActivityResult(
    private val launcher: ManagedActivityResultLauncher<Uri, Boolean>,
    private val imagePath: MutableState<String?>,
    private val imageId: MutableState<Int?>,
    private val fileUtils: FileUtils,
) {
    fun launch(id: Int? = null) {
        val filePath = fileUtils.createFilePath()
        imagePath.value = filePath
        imageId.value = id
        launcher.launch(fileUtils.getFileUri(filePath))
    }
}

@Composable
fun rememberGetContentActivityResult(
    contract: ActivityResultContract<Uri, Boolean> = ActivityResultContracts.TakePicture(),
    fileUtils: FileUtils = koinInject(),
    onSuccess: (String, Int?) -> Unit,
): GetContentActivityResult {
    val filePath = remember { mutableStateOf<String?>(null) }
    val fileId = remember { mutableStateOf<Int?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = contract,
        onResult = { success ->
            if (success) {
                filePath.value?.let { path ->
                    onSuccess(path, fileId.value)
                    fileId.value = null
                }
            }
        }
    )

    return remember(launcher) {
        GetContentActivityResult(
            launcher = launcher,
            imagePath = filePath,
            imageId = fileId,
            fileUtils = fileUtils,
        )
    }
}
