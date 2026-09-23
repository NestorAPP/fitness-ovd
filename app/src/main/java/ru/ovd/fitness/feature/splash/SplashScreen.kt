package ru.ovd.fitness.feature.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.draw.scale
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

    val numberAlpha = remember { Animatable(0f) }
    val numberScale = remember { Animatable(0.7f) }
    val titleAlpha  = remember { Animatable(0f) }
    val flagOffset  = remember { Animatable(80f) }

    LaunchedEffect(Unit) {
        numberAlpha.animateTo(1f, tween(700, easing = LinearEasing))
        numberScale.animateTo(1f, tween(700))
        titleAlpha.animateTo(1f, tween(500))
        flagOffset.animateTo(0f, tween(500))
        delay(700)
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
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "44",
                color = TriWhite,
                fontSize = 120.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(numberAlpha.value)
                    .scale(numberScale.value)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Физподготовка\nсотрудников ОВД",
                color = TriWhite,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp,
                modifier = Modifier.alpha(titleAlpha.value)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Приказ МВД № 44 от 2024 года",
                color = TriWhite.copy(alpha = 0.7f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(titleAlpha.value)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .offset(y = flagOffset.value.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(TriWhite)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(TriBlue)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(TriRed)
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
