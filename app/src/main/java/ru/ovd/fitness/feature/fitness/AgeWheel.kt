package ru.ovd.fitness.feature.fitness

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue

/**
 * Колесо выбора возраста (18..70).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgeWheel(
    selectedAge: Int,
    onAgeChange: (Int) -> Unit,
    minAge: Int = 18,
    maxAge: Int = 70,
    modifier: Modifier = Modifier
) {
    val ages = remember { (minAge..maxAge).toList() }

    // Высота одной строки
    val itemHeight = 56.dp
    // Видимая высота колеса (примерно 3 строки)
    val wheelHeight = itemHeight * 3
    // Отступ сверху и снизу, чтобы выбранный элемент был в центре
    val verticalPadding = itemHeight

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = (selectedAge - minAge).coerceAtLeast(0)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val context = LocalContext.current

    // Отслеживаем, какой элемент сейчас в центре
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                val centerIndex = index
                if (centerIndex in ages.indices) {
                    val newAge = ages[centerIndex]
                    if (newAge != selectedAge) {
                        onAgeChange(newAge)
                        playTick(context)
                    }
                }
            }
    }

    Box(
        modifier = modifier
            .width(160.dp)
            .height(wheelHeight),
        contentAlignment = Alignment.Center
    ) {
        // ─── Голубая подсветка центральной строки ───
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(TriBlue.copy(alpha = 0.08f))
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(vertical = verticalPadding),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(ages) { age ->
                val isSelected = age == selectedAge

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = age.toString(),
                        fontSize = if (isSelected) 30.sp else 22.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) OvdDarkBlue else TextSecondary
                    )
                }
            }
        }
    }
}

/**
 * Короткий щелчок + вибрация при прокрутке.
 */
private fun playTick(context: android.content.Context) {
    try {
        val toneGen = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 30)
        toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 15)
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            toneGen.release()
        }, 60)
    } catch (_: Exception) {}

    try {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val manager = context.getSystemService(VibratorManager::class.java)
            manager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Vibrator::class.java)
        }
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(VibrationEffect.createOneShot(15, 40))
            } else {
                @Suppress("DEPRECATION")
                it.vibrate(15)
            }
        }
    } catch (_: Exception) {}
}
