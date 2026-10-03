package ru.ovd.fitness.feature.combat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.feature.fitness.HeaderBlock

@Composable
fun CombatScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── ШАПКА ───
        HeaderBlock(title = "Боевые приёмы борьбы")

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ─── Иконка в круге ───
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(OvdLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    tint = OvdDarkBlue,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Раздел в разработке",
                color = OvdDarkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Здесь появятся видеоматериалы\nи текстовые описания боевых приёмов борьбы\nв соответствии с приказом МВД России",
                color = TextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
