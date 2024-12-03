package com.example.androidbasics.Intents

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.androidbasics.ui.theme.AndroidBasicsTheme

class SecondActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIncomingIntent(intent)
        setContent {
            AndroidBasicsTheme {

                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    viewModel.uri?.let {
                        Log.d("image" , "uri = ${viewModel.uri}")
                        AsyncImage(
                            model = viewModel.uri,
                            contentDescription = null
                        )
                    }

                    Text(
                        text = "Second Activity"
                    )
                    Button(
                        onClick = {
                            /* *//* Intent(applicationContext , SecondActivity::class.java).also {
                                startActivity(it)
                            }*//*
                            Intent(Intent.ACTION_MAIN).also {
                                it.`package`="com.google.android.youtube"
                              *//*  if (it.resolveActivity(packageManager) != null) {
                                    startActivity(it)
                                }*//*
                                // or
                                try {
                                    startActivity(it)
                                }catch(e : ActivityNotFoundException) {
                                    e.printStackTrace()
                                }
                            }*/
                            val Intent  = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_EMAIL,"test@test.com")
                                putExtra(Intent.EXTRA_SUBJECT,"this is a subject ")
                                putExtra(Intent.EXTRA_TEXT,"this is a text")
                            }
                            if (Intent.resolveActivity(packageManager) != null) {
                                startActivity(Intent)
                            }
                        }
                    ) {
                        Text(
                            text = "Click me "
                        )
                    }

                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIncomingIntent(intent)
    }

    fun handleIncomingIntent( intent: Intent?) {
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM , Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }

        if (uri != null) {
            viewModel.updateUri(uri)
        }
    }
}