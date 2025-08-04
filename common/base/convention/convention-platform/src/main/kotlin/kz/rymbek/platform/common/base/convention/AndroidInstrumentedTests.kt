package kz.rymbek.platform.common.base.convention

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension

internal fun LibraryAndroidComponentsExtension.disableAllTests() {
    return beforeVariants {
        it.androidTest.enable = false
        it.unitTestEnabled = false
    }
}

internal fun ApplicationAndroidComponentsExtension.disableAllTests() {
    return beforeVariants {
        it.androidTest.enable = false
        it.unitTestEnabled = false
    }
}