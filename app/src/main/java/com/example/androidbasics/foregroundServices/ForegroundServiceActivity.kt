package com.example.androidbasics.foregroundServices

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.androidbasics.ui.theme.AndroidBasicsTheme

class ForegroundServiceActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        setContent {
            AndroidBasicsTheme {

                Column(
                   modifier = Modifier
                       .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            Intent(applicationContext , ForegroundService::class.java).also {
                            it.action = ForegroundService.Actions.START.toString()
                                startService(it)
                            }
                        }
                    ) {
                        Text(
                            text = "Start Notification"
                        )
                    }
                    Button(
                        onClick = {
                            Intent(applicationContext , ForegroundService::class.java).also {
                                it.action = ForegroundService.Actions.STOP.toString()
                                    startService(it)
                            }
                        }
                    ) {
                        Text(
                            "Stop Notification"
                        )
                    }

                }

            }
        }
    }
}