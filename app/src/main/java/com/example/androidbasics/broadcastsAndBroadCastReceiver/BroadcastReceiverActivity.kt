package com.example.androidbasics.broadcastsAndBroadCastReceiver

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.androidbasics.ui.theme.AndroidBasicsTheme

class BroadcastReceiverActivity : ComponentActivity() {

    private val airplaneModeReceiver = AirplaneModeReceiver()
    private val testActionReceiver = TestActionReceiver()

    
    @SuppressLint("InlinedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            registerReceiver(
                airplaneModeReceiver,
                IntentFilter(
                    Intent.ACTION_AIRPLANE_MODE_CHANGED
                )
            )
        registerReceiver(
            testActionReceiver,
            IntentFilter("TEST_ACTION"),
            RECEIVER_NOT_EXPORTED

        )

        /*sendBroadcast(
            Intent("TEST_ACTION")
        )*/
        setContent{
            AndroidBasicsTheme {
                Greet()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(
            airplaneModeReceiver
        )
    }
}


@Composable
fun Greet() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = " Hello ",
            fontSize = 24.sp
        )
    }
}