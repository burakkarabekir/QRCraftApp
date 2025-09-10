package com.bksd.qrcraftapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import com.bksd.qrcraftapp.app.navigation.NavigationRoot
import com.bksd.qrcraftapp.core.ui.design_system.theme.QRCraftAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QRCraftAppTheme {
                val backgroundColor = MaterialTheme.colorScheme.onSurface.toArgb()

                SideEffect {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(backgroundColor, backgroundColor),
                        navigationBarStyle = SystemBarStyle.light(backgroundColor, backgroundColor)
                    )
                }

                NavigationRoot()
            }
        }
    }
}