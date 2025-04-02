package kz.rymbek.platform.common.core.design.foundation.theme.color

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.BaseColorScheme

internal class DynamicColorScheme(
    context: Context
) : BaseColorScheme() {
    @RequiresApi(Build.VERSION_CODES.S)
    override val darkScheme: ColorScheme = dynamicDarkColorScheme(context)

    @RequiresApi(Build.VERSION_CODES.S)
    override val lightScheme: ColorScheme = dynamicLightColorScheme(context)
}