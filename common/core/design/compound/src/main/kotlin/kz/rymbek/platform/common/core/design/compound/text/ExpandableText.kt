package kz.rymbek.platform.common.core.design.compound.text

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kz.rymbek.platform.common.core.design.foundation.components.button.regular.AppOutlinedButton
import kz.rymbek.platform.common.core.design.foundation.components.text.AppText

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    initialExpanded: Boolean = false,
) {
    var expanded by rememberSaveable { mutableStateOf(initialExpanded) }
    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        AppText(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else 5,
            style = MaterialTheme.typography.bodySmall,
        )
        AppOutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = if (expanded) "Скрыть" else "Показать больше",
            onClick = { expanded = !expanded },
            border = null,
        )
    }
}