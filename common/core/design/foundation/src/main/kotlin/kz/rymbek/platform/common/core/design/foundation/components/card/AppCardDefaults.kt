package kz.rymbek.platform.common.core.design.foundation.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import kz.rymbek.platform.common.core.design.foundation.constants.corner.PlatformShape

object AppCardDefaults {
    val filledShape: Shape
        @Composable get() = PlatformShape.small

    val elevatedShape: Shape
        @Composable get() = PlatformShape.small

    val outlinedShape: Shape
        @Composable get() = PlatformShape.small

    @Composable
    fun cardColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = contentColorFor(containerColor),
        disabledContainerColor: Color = Color.Unspecified,
    ): CardColors = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
    )

    @Composable
    fun filledCardElevation(): CardElevation = CardDefaults.cardElevation()

    @Composable
    fun outlinedCardElevation(): CardElevation = CardDefaults.outlinedCardElevation()

    @Composable
    fun elevatedCardElevation(): CardElevation = CardDefaults.elevatedCardElevation()

    val outlinedBorder: BorderStroke
        @Composable get() = CardDefaults.outlinedCardBorder()
}