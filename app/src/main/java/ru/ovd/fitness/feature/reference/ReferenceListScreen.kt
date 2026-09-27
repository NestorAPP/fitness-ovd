package ru.ovd.fitness.feature.reference

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun ReferenceListScreen(
    navController: NavHostController,
    viewModel: ReferenceListViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Справочник",
                style = MaterialTheme.typography.headlineSmall,
                color = OvdDarkBlue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .width(80.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
            ) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriWhite))
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriBlue))
                Box(modifier = Modifier.weight(1f).fillMaxHeight().background(TriRed))
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = OvdDarkBlue)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                val grouped = state.exercises.groupBy { it.category }

                grouped.forEach { (category, exercises) ->
                    item {
                        Text(
                            text = category.uppercase(),
                            style = MaterialTheme.typography.labelLarge,
                            color = TextSecondary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                start = 4.dp,
                                top = 16.dp,
                                bottom = 8.dp
                            )
                        )
                    }

                    items(exercises) { exercise ->
                        ExerciseCard(
                            exercise = exercise,
                            onClick = {
                                navController.navigate(
                                    "reference_detail?orderNumber=${exercise.orderNumber}"
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
private fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(OvdLightBlue)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = exercise.unit,
                color = TextSecondary,
                fontSize = 13.sp
            )
        }
        Text(
            text = "→",
            color = OvdDarkBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
