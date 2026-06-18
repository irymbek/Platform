package kz.rymbek.platform.common.base.convention

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.DeviceTestBuilder
import com.android.build.api.variant.HasDeviceTestsBuilder
import com.android.build.api.variant.HasUnitTestBuilder

internal fun AndroidComponentsExtension<*, *, *>.disableAllTests() = beforeVariants {
    if (it is HasUnitTestBuilder) {
        it.enableUnitTest = false
    }

    if (it is HasDeviceTestsBuilder) {
        it.deviceTests[DeviceTestBuilder.ANDROID_TEST_TYPE]?.enable = false
    }
}
