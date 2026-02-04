package kz.rymbek.platform.common.core.activity

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kz.rymbek.platform.common.core.file.FileUtils
import org.koin.compose.koinInject

@Composable
fun rememberCameraLauncher(
    contract: ActivityResultContract<Uri, Boolean> = ActivityResultContracts.TakePicture(),
    fileUtils: FileUtils = koinInject(),
    onSuccess: (path: String, id: Int?) -> Unit
): CameraLauncher {
    val currentPath = remember { mutableStateOf<String?>(null) }
    val currentId = remember { mutableStateOf<Int?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = contract,
        onResult = { success ->
            if (success) {
                currentPath.value?.let { path ->
                    onSuccess(path, currentId.value)
                }
            }
            currentPath.value = null
            currentId.value = null
        }
    )

    return remember(launcher) {
        object : CameraLauncher {
            override fun launch(id: Int?) {
                val path = fileUtils.createFile()
                val uri = fileUtils.getFileUri(path)
                currentPath.value = path.absolutePath
                currentId.value = id
                launcher.launch(uri)
            }
        }
    }
}

interface CameraLauncher {
    fun launch(id: Int? = null)
}