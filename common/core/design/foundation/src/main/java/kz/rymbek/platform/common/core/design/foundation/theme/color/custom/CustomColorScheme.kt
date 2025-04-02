package kz.rymbek.platform.common.core.design.foundation.theme.color.custom

import kz.rymbek.platform.common.core.design.foundation.theme.color.base.neutralContainerDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.neutralContainerLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.neutralDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.neutralLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onNeutralContainerDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onNeutralContainerLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onNeutralDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onNeutralLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onSuccessContainerDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onSuccessContainerLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onSuccessDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.onSuccessLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.successContainerDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.successContainerLight
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.successDark
import kz.rymbek.platform.common.core.design.foundation.theme.color.base.successLight

internal object CustomColorScheme {
    val darkScheme: ColorCustomData = ColorCustomData(
        success = successDark,
        onSuccess = onSuccessDark,
        successContainer = successContainerDark,
        onSuccessContainer = onSuccessContainerDark,

        neutral = neutralDark,
        onNeutral = onNeutralDark,
        neutralContainer = neutralContainerDark,
        onNeutralContainer = onNeutralContainerDark,
    )
    val lightScheme: ColorCustomData = ColorCustomData(
        success = successLight,
        onSuccess = onSuccessLight,
        successContainer = successContainerLight,
        onSuccessContainer = onSuccessContainerLight,

        neutral = neutralLight,
        onNeutral = onNeutralLight,
        neutralContainer = neutralContainerLight,
        onNeutralContainer = onNeutralContainerLight,
    )
}