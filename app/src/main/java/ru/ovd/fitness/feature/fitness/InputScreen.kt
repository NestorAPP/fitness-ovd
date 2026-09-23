package ru.ovd.fitness.feature.fitness

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun InputScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Экран ввода",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Здесь будут тумблер пола,\nколесо возраста и выбор уровня",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("fitness_result") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("→ Результат")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("fitness_reference") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("→ Справочник")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("fitness_calculator") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("→ Калькулятор")
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.navigate("fitness_recovery") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("→ Программа восстановления")
        }
    }
}
