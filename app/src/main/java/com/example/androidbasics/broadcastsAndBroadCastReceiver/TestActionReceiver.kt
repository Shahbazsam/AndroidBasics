package com.example.androidbasics.broadcastsAndBroadCastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TestActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent?.action == "TEST_ACTION") {
            println("Received Test Action ")
        }
    }
}