package kz.rymbek.platform.common.core.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.koin.core.annotation.Single

@Single
class ActivityUtils(
    private val context: Context,
) {
    private val activity by lazy {
        context.findActivity()
    }

    private fun Context.findActivity(): Activity {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) return context
            context = context.baseContext ?: break
        }
        throw IllegalStateException("findActivity should be called in the context of an Activity")
    }

    fun openAppSettings() {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", activity.packageName, null)
        )
        activity.startActivity(intent)
    }
}

@Composable
fun rememberActivityUtils(): ActivityUtils {
    val context = LocalContext.current
    return remember(context) {
        ActivityUtils(context)
    }
}