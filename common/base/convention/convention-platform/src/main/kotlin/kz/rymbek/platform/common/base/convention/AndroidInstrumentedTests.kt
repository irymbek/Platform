package kz.rymbek.platform.common.base.convention

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension

/*internal fun CommonExtension.disableUnitTests() {
    return testOptions.unitTests
        .all {
            it.enabled = false
            it.isScanForTestClasses = false
        }
}

internal fun AndroidComponentsExtension<*,*,*>.disableAllTests() {

    return beforeVariants {
        it.enable = false
        /*if (builder is HasUnitTestBuilder) {
            builder.enableUnitTest = false
        }

        if (builder is HasDeviceTestsBuilder) {
            builder.deviceTests.forEach { (_, testBuilder) ->
                testBuilder.enable = false
            }
        }*/
    }
}*/


internal fun LibraryAndroidComponentsExtension.disableAllTests() {
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
}