package ru.ovd.fitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import ru.ovd.fitness.core.ui.theme.BackgroundSoft
import ru.ovd.fitness.core.ui.theme.FitnessOVDTheme
import ru.ovd.fitness.feature.home.HomeScreen
import ru.ovd.fitness.feature.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
