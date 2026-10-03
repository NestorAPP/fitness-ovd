package ru.ovd.fitness.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import ru.ovd.fitness.core.ui.theme.OvdLightBlue
import ru.ovd.fitness.core.ui.theme.SurfaceWhite
import ru.ovd.fitness.core.ui.theme.TextPrimary
import ru.ovd.fitness.core.ui.theme.TextSecondary
import ru.ovd.fitness.feature.fitness.HeaderBlock

@Composable
fun AboutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSoft)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ─── ШАПКА ───
        HeaderBlock(title = "О приложении")

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ─── Название ───
            InfoCard {
                Text(
                    text = "ВИС БРИЗ",
                    color = OvdDarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Виртуальная интегральная система\nбальных результатов итоговых занятий",
                    color = TextSecondary,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // ─── Версия ───
            InfoCard {
                InfoRow(label = "Версия", value = "1.0")
            }

            // ─── Разработчики ───
            InfoCard {
                Text(
                    text = "Разработчики",
                    color = OvdDarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                PersonRow("Нестерёнок Ю.В.")
                PersonRow("Сидлецкий А.В.")
                PersonRow("Иликбаева Е.С.")
            }

            // ─── Организация ───
            InfoCard {
                Text(
                    text = "Организация",
                    color = OvdDarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Крымский филиал Краснодарского университета МВД России",
                    color = TextPrimary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun InfoCard(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite)
            .padding(18.dp),
        content = content
    )
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = TextSecondary, fontSize = 14.sp)
        Text(text = value, color = OvdDarkBlue, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
private fun PersonRow(name: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "• ",
            color = OvdDarkBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
        Text(
            text = name,
            color = TextPrimary,
            fontSize = 14.sp,
            lineHeight = 22.sp
        )
    }
}
