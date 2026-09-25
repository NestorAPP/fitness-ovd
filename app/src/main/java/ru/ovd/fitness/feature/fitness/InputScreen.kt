package ru.ovd.fitness.feature.fitness

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun InputScreen(
    navController: NavHostController,
    viewModel: InputViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Физическая подготовка",
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

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("Пол")
        Spacer(modifier = Modifier.height(12.dp))
        GenderToggle(state.gender) { viewModel.setGender(it) }

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("Возраст")
        Spacer(modifier = Modifier.height(12.dp))
        AgeWheel(
            selectedAge = state.age,
            onAgeChange = { viewModel.setAge(it) }
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("Уровень подготовки")
        Spacer(modifier = Modifier.height(12.dp))

        LevelOption("Базовый",
            "Для сотрудников, не являющихся сотрудниками полиции, и 4 группы предназначения",
            state.level == "base"
        ) { viewModel.setLevel("base") }
        Spacer(modifier = Modifier.height(10.dp))

        LevelOption("Усиленный",
            "Для сотрудников полиции (1–3 группы предназначения)",
            state.level == "enhanced"
        ) { viewModel.setLevel("enhanced") }
        Spacer(modifier = Modifier.height(10.dp))

        LevelOption("Специальный",
            "Для спецподразделений (СОБР, ОМОН и т.д.)",
            state.level == "special"
        ) { viewModel.setLevel("special") }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate(
                    "fitness_result?gender=${state.gender}&age=${state.age}&level=${state.level}"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OvdDarkBlue,
                contentColor = TriWhite
            ),
            enabled = state.isValid
        ) {
            Text(
                text = "Рассчитать нормативы",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = OvdDarkBlue,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun GenderToggle(gender: String, onGenderChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GenderToggleSide("Мужской", gender == "male",
            Modifier.weight(1f)) { onGenderChange("male") }
        GenderToggleSide("Женский", gender == "female",
            Modifier.weight(1f)) { onGenderChange("female") }
    }
}

@Composable
private fun GenderToggleSide(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val bg by animateColorAsState(
        if (selected) OvdDarkBlue else Color.Transparent,
        tween(250), label = "genderBg"
    )
    val fg by animateColorAsState(
        if (selected) TriWhite else TextSecondary,
        tween(250), label = "genderFg"
    )

    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(bg)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = fg,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun LevelOption(
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg by animateColorAsState(
        if (selected) OvdLightBlue else SurfaceWhite,
        tween(250), label = "levelBg"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(20.dp)
                .clip(CircleShape)
                .background(if (selected) OvdDarkBlue else TextSecondary.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(TriWhite)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = OvdDarkBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}
