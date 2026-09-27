package ru.ovd.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.data.AppDatabase
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.FitnessOVDTheme
import ru.ovd.fitness.feature.home.HomeScreen
import ru.ovd.fitness.feature.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ─── ПРОГРЕВ БАЗЫ ДАННЫХ ───
        // Запускаем в фоне, чтобы база заполнилась
        // до того, как пользователь откроет «Справочник».
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val db = AppDatabase.getDatabase(applicationContext)
                // Простой запрос — триггерит onCreate и заполнение
                db.dao().countAgeGroups()
            } catch (_: Exception) {
                // Игнорируем — база заполнится при первом реальном запросе
            }
        }

        setContent {
            FitnessOVDTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundSoft
                ) {
                    var showSplash by remember { mutableStateOf(true) }

                    if (showSplash) {
                        SplashScreen(onFinished = { showSplash = false })
                    } else {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
