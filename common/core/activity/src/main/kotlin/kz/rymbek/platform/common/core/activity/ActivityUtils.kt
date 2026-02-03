package kz.rymbek.platform.common.core.activity

import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Rational
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


    @Composable
    fun EnterInPipAuto(enabled: Boolean, aspectRatio: Rational? = null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val builder = PictureInPictureParams.Builder()
            builder.setAutoEnterEnabled(true)
            context.findActivity().setPictureInPictureParams(builder.build())
        } else {
            /*DisposableEffect(context) {
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
            }*/
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