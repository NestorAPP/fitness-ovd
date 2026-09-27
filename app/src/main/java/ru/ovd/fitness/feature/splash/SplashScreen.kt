package ru.ovd.fitness.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun SplashScreen(onFinished: () -> Unit) {

    // Анимации:
    // 1. Белая полоса выезжает слева
    // 2. Синяя — сверху
    // 3. Красная — справа
    // 4. Название проявляется под флагом

    val whiteOffset  = remember { Animatable(-400f) }
    val blueOffset   = remember { Animatable(-400f) }
    val redOffset    = remember { Animatable(400f) }

    val titleAlpha   = remember { Animatable(0f) }
    val subtitleAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Белая — слева
        whiteOffset.animateTo(0f, tween(400))
        // Синяя — сверху
        blueOffset.animateTo(0f, tween(400))
        // Красная — справа
        redOffset.animateTo(0f, tween(400))

        // Название
        titleAlpha.animateTo(1f, tween(500))
        subtitleAlpha.animateTo(1f, tween(400))

        delay(800)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OvdDarkBlue)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ─── ФЛАГ: три полосы ───
            Column(
                modifier = Modifier
                    .width(220.dp)
                    .height(132.dp)
            ) {
                // Белая — выезжает слева
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .offset(x = whiteOffset.value.dp)
                        .background(TriWhite)
                )
                // Синяя — выезжает сверху
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .offset(y = blueOffset.value.dp / 3)
                        .background(TriBlue)
                )
                // Красная — выезжает справа
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .offset(x = redOffset.value.dp)
                        .background(TriRed)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ─── Название ───
            Text(
                text = "Физо ОВД",
                color = TriWhite,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(titleAlpha.value)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Физическая подготовка",
                color = TriWhite.copy(alpha = 0.7f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(subtitleAlpha.value)
            )
        }
    }
}
