package ru.ovd.fitness.feature.reference

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriWhite
import ru.ovd.fitness.feature.fitness.HeaderBlock

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ReferenceDetailScreen(
    navController: NavHostController,
    orderNumber: Int,
    viewModel: ReferenceDetailViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(orderNumber) {
        viewModel.load(orderNumber)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── ШАПКА ───
        HeaderBlock(title = state.exerciseName.ifEmpty { "Упражнение" })

        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = OvdDarkBlue)
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize().padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            else -> {
                Spacer(modifier = Modifier.height(20.dp))

                // ─── Колесо результатов ───
                ResultWheel(
                    scores = state.scores,
                    selectedIndex = state.selectedIndex,
                    onIndexChange = { viewModel.selectIndex(it) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ─── Балл ───
                state.currentScore?.let { score ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(OvdDarkBlue)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Твой балл",
                            color = TriWhite.copy(alpha = 0.8f),
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = score.points.toString(),
                            color = TriWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 56.sp
                        )
                        Text(
                            text = "баллов",
                            color = TriWhite.copy(alpha = 0.8f),
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ─── Кнопка «Назад» ───
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OvdDarkBlue,
                        contentColor = TriWhite
                    )
                ) {
                    Text(
                        text = "←  Назад",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
private fun ResultWheel(
    scores: List<ru.ovd.fitness.core.data.entity.ExerciseScore>,
    selectedIndex: Int,
    onIndexChange: (Int) -> Unit
) {
    if (scores.isEmpty()) return

    val itemHeight = 56.dp
    val wheelHeight = itemHeight * 3

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = selectedIndex
    )
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index in scores.indices) {
                    onIndexChange(index)
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(wheelHeight),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp)
                .height(itemHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(TriBlue.copy(alpha = 0.08f))
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(vertical = itemHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(scores) { index, score ->
                val isSelected = index == selectedIndex

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = score.resultDisplay,
                        fontSize = if (isSelected) 28.sp else 20.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) OvdDarkBlue else TextSecondary
                    )
                }
            }
        }
    }
}
