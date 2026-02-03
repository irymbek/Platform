package kz.rymbek.platform.common.core.activity

import android.app.PictureInPictureParams
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import org.koin.core.annotation.Single

@Single
class ActivityUtils(
    private val context: Context,
) {
    private val activity by lazy {
        context.findActivity()
    }

    private fun Context.findActivity(): ComponentActivity {
        var context = this
        while (context is ContextWrapper) {
            if (context is ComponentActivity) return context
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

    fun systemBarConfiguration() {
        val window = activity.window
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller.hide(WindowInsetsCompat.Type.systemBars())
        }
    }

    @Composable
    fun EnterInPipAuto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val builder = PictureInPictureParams.Builder()
            builder.setAutoEnterEnabled(true)
            context.findActivity().setPictureInPictureParams(builder.build())
        } else {
            DisposableEffect(context) {
                val onUserLeaveBehavior = Runnable {
                    context.findActivity()
                        .enterPictureInPictureMode(PictureInPictureParams.Builder().build())
                }
                context.findActivity().addOnUserLeaveHintListener(
                    onUserLeaveBehavior
                )
                onDispose {
                    context.findActivity().removeOnUserLeaveHintListener(
                        onUserLeaveBehavior
                    )
                }
            }
        }
    }

    fun toggleOrientation() {
        val isLandscape = activity.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        activity.requestedOrientation = if (isLandscape) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        }
    }
}

@Composable
fun rememberActivityUtils(): ActivityUtils {
    val context = LocalContext.current
    return remember(context) {
        ActivityUtils(context)
    }
}