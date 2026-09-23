package ru.ovd.fitness.feature.shooting

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.runtime.Composable
import ru.ovd.fitness.core.ui.components.ComingSoonScreen

@Composable
fun ShootingScreen() {
    ComingSoonScreen(
        title = "Огневая подготовка",
        subtitle = "Раздел в разработке.\n\nЗдесь появятся нормативы и упражнения по огневой подготовке.",
        icon = Icons.Default.MyLocation
    )
}
