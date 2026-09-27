package ru.ovd.fitness.feature.recommendations

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue

/**
 * Колесо выбора веса (40..200 кг).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeightWheel(
    selectedWeight: Int,
    onWeightChange: (Int) -> Unit,
    minWeight: Int = 40,
    maxWeight: Int = 200,
    modifier: Modifier = Modifier
) {
    val weights = remember { (minWeight..maxWeight).toList() }

    val itemHeight = 56.dp
    val wheelHeight = itemHeight * 3
    val verticalPadding = itemHeight

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = (selectedWeight - minWeight).coerceAtLeast(0)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                if (index in weights.indices) {
                    val newWeight = weights[index]
                    if (newWeight != selectedWeight) {
                        onWeightChange(newWeight)
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
            items(weights) { w ->
                val isSelected = w == selectedWeight

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = w.toString(),
                        fontSize = if (isSelected) 30.sp else 22.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) OvdDarkBlue else TextSecondary
                    )
                }
            }
        }
    }
}
