package ru.ovd.fitness.feature.recommendations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.ovd.fitness.core.data.UserPreferences
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.OvdDarkBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.core.ui.theme.TriBlue
import ru.ovd.fitness.core.ui.theme.TriRed
import ru.ovd.fitness.core.ui.theme.TriWhite

@Composable
fun RecommendationsDisclaimerScreen(navController: NavHostController) {

    val context = LocalContext.current
    val prefs = UserPreferences(context)

    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── Значок предупреждения ───
        Text(
            text = "⚠",
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ─── Заголовок ───
        Text(
            text = "Важное уведомление",
            style = MaterialTheme.typography.headlineSmall,
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

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

        Spacer(modifier = Modifier.height(20.dp))

        // ─── Текст дисклеймера ───
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(SurfaceWhite)
                .padding(20.dp)
        ) {
            DisclaimerParagraph(
                "Приложение «Физо ОВД» является справочным инструментом " +
                        "и не заменяет профессиональную консультацию."
            )

            Spacer(modifier = Modifier.height(12.dp))

            DisclaimerParagraph(
                "Перед началом любой программы тренировок рекомендуется:"
            )

            Spacer(modifier = Modifier.height(6.dp))

            DisclaimerBullet("Проконсультироваться с врачом и получить допуск к физическим нагрузкам.")
            DisclaimerBullet("Учесть индивидуальные особенности: состояние здоровья, хронические заболевания, уровень подготовленности, возраст.")
            DisclaimerBullet("При появлении боли, головокружения, одышки — немедленно прекратить выполнение упражнений и обратиться к врачу.")

            Spacer(modifier = Modifier.height(12.dp))

            DisclaimerParagraph(
                "Авторский коллектив разработчиков не несёт ответственности " +
                        "за возможные травмы, ухудшение состояния здоровья или иные последствия, " +
                        "возникшие в результате использования приложения."
            )

            Spacer(modifier = Modifier.height(12.dp))

            DisclaimerParagraph(
                "Все нормативы и баллы приведены в соответствии с действующими " +
                        "приказами МВД России и носят справочный характер. " +
                        "Актуальную информацию уточняйте в официальных источниках."
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ─── Галочка «Я ознакомлен» ───
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceWhite)
                .clickable { checked = !checked }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = OvdDarkBlue,
                    uncheckedColor = TextSecondary
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Я ознакомлен и согласен с условиями",
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ─── Кнопка «Продолжить» ───
        Button(
            onClick = {
                prefs.setRecommendationsDisclaimerAccepted(true)
                navController.navigate("recommendations_input") {
                    popUpTo("recommendations_disclaimer") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OvdDarkBlue,
                contentColor = TriWhite,
                disabledContainerColor = TextSecondary.copy(alpha = 0.3f),
                disabledContentColor = TriWhite.copy(alpha = 0.5f)
            ),
            enabled = checked
        ) {
            Text(
                text = "Продолжить",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun DisclaimerParagraph(text: String) {
    Text(
        text = text,
        color = TextPrimary,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
}

@Composable
private fun DisclaimerBullet(text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "• ",
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        Text(
            text = text,
            color = TextPrimary,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}
