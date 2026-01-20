package kz.rymbek.platform.common.base.convention

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension

internal fun LibraryAndroidComponentsExtension.disableAllTests() {
    return beforeVariants {
        it.androidTest.enable = false
        //it.unitTestEnabled = false
    }
}

internal fun ApplicationAndroidComponentsExtension.disableAllTests() {
    return beforeVariants {
        //it.androidTest.enable = false
        (it as? com.android.build.api.variant.HasUnitTestBuilder)?.enableUnitTest = false
        (it as? com.android.build.api.variant.HasAndroidTestBuilder)?.androidTest?.enable = false
        //it.unitTestEnabled = false
    }
}