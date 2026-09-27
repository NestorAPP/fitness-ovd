package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
 * Колесо выбора роста (140..220 см).
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeightWheel(
    selectedHeight: Int,
    onHeightChange: (Int) -> Unit,
    minHeight: Int = 140,
    maxHeight: Int = 220,
    modifier: Modifier = Modifier
) {
    val heights = remember { (minHeight..maxHeight).toList() }

    val itemHeight = 56.dp
    val wheelHeight = itemHeight * 3
    val verticalPadding = itemHeight

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = (selectedHeight - minHeight).coerceAtLeast(0)
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                if (index in heights.indices) {
                    val newHeight = heights[index]
                    if (newHeight != selectedHeight) {
                        onHeightChange(newHeight)
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
            items(heights) { h ->
                val isSelected = h == selectedHeight

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = h.toString(),
                        fontSize = if (isSelected) 30.sp else 22.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) OvdDarkBlue else TextSecondary
                    )
                }
            }
        }
    }
}
