package ru.ovd.fitness.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun SplashScreen(onFinished: () -> Unit) {

    // ─── Появление флага ───
    val flagAlpha = remember { Animatable(0f) }
    val flagScale = remember { Animatable(0.7f) }

    // ─── Появление текстов ───
    val titleAlpha = remember { Animatable(0f) }
    val subtitleAlpha = remember { Animatable(0f) }

    // ─── Развевание (бесконечная анимация смещения) ───
    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val waveX by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "waveX"
    )
    val waveY by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "waveY"
    )

    LaunchedEffect(Unit) {
        // Флаг появляется
        flagAlpha.animateTo(1f, tween(600))
        flagScale.animateTo(1f, tween(600))

        // Название
        delay(200)
        titleAlpha.animateTo(1f, tween(500))

        // Расшифровка
        delay(200)
        subtitleAlpha.animateTo(1f, tween(400))

        // Пауза — покажем красоту
        delay(900)
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

            // ─── ФЛАГ (развевается) ───
            Box(
                modifier = Modifier
                    .scale(flagScale.value)
                    .alpha(flagAlpha.value)
                    .offset(x = waveX.dp, y = waveY.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(220.dp)
                        .height(132.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(TriWhite)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(TriBlue)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(TriRed)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

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
