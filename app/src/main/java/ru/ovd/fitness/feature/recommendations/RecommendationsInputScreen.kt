package ru.ovd.fitness.feature.recommendations

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriWhite
import ru.ovd.fitness.feature.fitness.AgeWheel
import ru.ovd.fitness.feature.fitness.HeaderBlock

@Composable
fun RecommendationsInputScreen(
    navController: NavHostController,
    viewModel: RecommendationsInputViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── ШАПКА ───
        HeaderBlock(title = "Твои параметры")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

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

            SectionTitle("Рост, см")
            Spacer(modifier = Modifier.height(12.dp))
            HeightWheel(
                selectedHeight = state.height,
                onHeightChange = { viewModel.setHeight(it) }
            )

            Spacer(modifier = Modifier.height(28.dp))

            SectionTitle("Вес, кг")
            Spacer(modifier = Modifier.height(12.dp))
            WeightWheel(
                selectedWeight = state.weight,
                onWeightChange = { viewModel.setWeight(it) }
            )

            Spacer(modifier = Modifier.height(28.dp))

            SectionTitle("Уровень активности")
            Spacer(modifier = Modifier.height(12.dp))

            ActivityOption(
                number = 1,
                title = "Очень высокий",
                description = "Спорт 5 и более раз в неделю. Профессиональный уровень или близкий к нему.",
                selected = state.activityLevel == 1
            ) { viewModel.setActivityLevel(1) }
            Spacer(modifier = Modifier.height(10.dp))

            ActivityOption(
                number = 2,
                title = "Высокий",
                description = "Спорт 3–4 раза в неделю. Регулярные тренировки, хорошая физическая форма.",
                selected = state.activityLevel == 2
            ) { viewModel.setActivityLevel(2) }
            Spacer(modifier = Modifier.height(10.dp))

            ActivityOption(
                number = 3,
                title = "Средний",
                description = "Спорт 1–2 раза в неделю, нерегулярно. Ежедневная активность умеренная.",
                selected = state.activityLevel == 3
            ) { viewModel.setActivityLevel(3) }
            Spacer(modifier = Modifier.height(10.dp))

            ActivityOption(
                number = 4,
                title = "Низкий",
                description = "Спорта почти нет. Только бытовая активность. Много времени сижу.",
                selected = state.activityLevel == 4
            ) { viewModel.setActivityLevel(4) }
            Spacer(modifier = Modifier.height(10.dp))

            ActivityOption(
                number = 5,
                title = "Очень низкий",
                description = "Спорта нет. Сидячая работа. Ежедневная активность минимальна.",
                selected = state.activityLevel == 5
            ) { viewModel.setActivityLevel(5) }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate("recommendations_maintenance")
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
                    text = "Показать рекомендации",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
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
private fun ActivityOption(
    number: Int,
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val bg by animateColorAsState(
        if (selected) OvdLightBlue else SurfaceWhite,
        tween(250), label = "activityBg"
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
                text = "$number. $title",
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
