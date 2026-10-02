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

    val whiteOffset = remember { Animatable(-400f) }
    val blueOffset = remember { Animatable(-400f) }
    val redOffset = remember { Animatable(400f) }

    val titleAlpha = remember { Animatable(0f) }
    val subtitleAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Полосы выезжают
        whiteOffset.animateTo(0f, tween(400))
        blueOffset.animateTo(0f, tween(400))
        redOffset.animateTo(0f, tween(400))

        // Название
        titleAlpha.animateTo(1f, tween(500))

        // Небольшая пауза между названием и расшифровкой
        delay(200)

        // Расшифровка
        subtitleAlpha.animateTo(1f, tween(400))

        // ─── Пауза, чтобы прочитать ───
        delay(1800)
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
            // ─── ФЛАГ ───
            Column(
                modifier = Modifier
                    .width(220.dp)
                    .height(132.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .offset(x = whiteOffset.value.dp)
                        .background(TriWhite)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .offset(y = blueOffset.value.dp / 3)
                        .background(TriBlue)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .offset(x = redOffset.value.dp)
                        .background(TriRed)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // ─── Название ───
            Text(
                text = "ВИС БРИЗ",
                color = TriWhite,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 4.sp,
                modifier = Modifier.alpha(titleAlpha.value)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ─── Расшифровка ───
            Text(
                text = "Виртуальная интегральная\nсистема бальных результатов\nитоговых занятий",
                color = TriWhite.copy(alpha = 0.7f),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.alpha(subtitleAlpha.value)
            )
        }
    }
}
