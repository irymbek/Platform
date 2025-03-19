package kz.rymbek.platform.convention

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.ApplicationVariantBuilder
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.api.variant.LibraryVariantBuilder

internal fun LibraryAndroidComponentsExtension.disableAllTests() {
    return beforeVariants(
        selector = selector().all(),
        callback = { libraryVariantBuilder: LibraryVariantBuilder ->
            libraryVariantBuilder.androidTest.enable = false
            libraryVariantBuilder.unitTestEnabled = false
        }
    )
}

internal fun ApplicationAndroidComponentsExtension.disableAllTests() {
    return beforeVariants(
        selector = selector().all(),
        callback = { applicationVariantBuilder: ApplicationVariantBuilder ->
            applicationVariantBuilder.androidTest.enable = false
            applicationVariantBuilder.unitTestEnabled = false
        }
    )
}