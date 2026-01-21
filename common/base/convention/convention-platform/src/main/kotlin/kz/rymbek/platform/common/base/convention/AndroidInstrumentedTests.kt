package kz.rymbek.platform.common.base.convention

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.DeviceTestBuilder
import com.android.build.api.variant.HasDeviceTestsBuilder
import com.android.build.api.variant.HasUnitTestBuilder

internal fun AndroidComponentsExtension<*, *, *>.disableAllTests() {
    beforeVariants {
        if (it is HasUnitTestBuilder) {
            it.enableUnitTest = false
        }

        if (it is HasDeviceTestsBuilder) {
            it.deviceTests[DeviceTestBuilder.ANDROID_TEST_TYPE]?.enable = false
        }
    }
}

/*internal fun LibraryAndroidComponentsExtension.disableAllTests() {
    return beforeVariants {
        it.androidTest.enable = false
        //it.unitTestEnabled = false
    }
}

internal fun ApplicationAndroidComponentsExtension.disableAllTests() {
    return beforeVariants {
        it.androidTest.enable = false
        //(it as? com.android.build.api.variant.HasUnitTestBuilder)?.enableUnitTest = false
        //(it as? com.android.build.api.variant.HasAndroidTestBuilder)?.androidTest?.enable = false
        //it.unitTestEnabled = false
    }
}*/