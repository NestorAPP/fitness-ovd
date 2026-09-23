package ru.ovd.fitness.feature.fitness

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun InputScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .padding(horizontal = 24.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── Заголовок ───
        Text(
            text = "Физическая подготовка",
            style = MaterialTheme.typography.headlineSmall,
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ─── Полоска триколора ───
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

        Spacer(modifier = Modifier.height(16.dp))

        // ─── Подзаголовок ───
        Text(
            text = "Здесь будут тумблер пола,\nколесо возраста и выбор уровня",
            style = MaterialTheme.typography.bodyLarge,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // ─── Кнопки ───
        MenuButton(
            text = "→  Результат",
            onClick = { navController.navigate("fitness_result") }
        )
        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            text = "→  Справочник",
            onClick = { navController.navigate("fitness_reference") }
        )
        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            text = "→  Калькулятор",
            onClick = { navController.navigate("fitness_calculator") }
        )
        Spacer(modifier = Modifier.height(12.dp))

        MenuButton(
            text = "→  Программа восстановления",
            onClick = { navController.navigate("fitness_recovery") },
            isAccent = true
        )
    }
}

@Composable
private fun MenuButton(
    text: String,
    onClick: () -> Unit,
    isAccent: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isAccent) TriRed else OvdDarkBlue,
            contentColor = TriWhite
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}
