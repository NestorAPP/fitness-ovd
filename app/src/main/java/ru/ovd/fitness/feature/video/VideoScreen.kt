package ru.ovd.fitness.feature.video

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import ru.ovd.fitness.core.ui.components.ComingSoonScreen

@Composable
fun VideoScreen() {
    ComingSoonScreen(
        title = "Видеоприёмы",
        subtitle = "Раздел в разработке.\n\nЗдесь появятся обучающие видеоматериалы.",
        icon = Icons.Default.PlayCircle
    )
}
