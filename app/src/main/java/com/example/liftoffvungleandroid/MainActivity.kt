package com.example.liftoffvungleandroid

import HomeScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.liftoffvungleandroid.ui.theme.LiftoffVungleAndroidTheme
import com.vungle.ads.InitializationListener
import com.vungle.ads.VungleAds
import com.vungle.ads.VungleError

class MainActivity : ComponentActivity() {
    private val TAG = "VungleAdManager" //Tag for logging
    private val appID = "67b80530def9e46cb8d322aa" //Vungle App ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize AdManager
        val adManager = AdManager(this)

        //Initialize Vungle SDK
        VungleAds.init(this, appID, object : InitializationListener {
            override fun onSuccess() {
                Log.d(TAG, "Vungle SDK init onSuccess()")
                adManager.loadBanner() //load banners after Vungle SDK is initialized
            }
            override fun onError(vungleError: VungleError) {
                Log.d(TAG, "onError(): ${vungleError.localizedMessage}")
            }
        })
        enableEdgeToEdge()
        setContent {
            LiftoffVungleAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        adManager, //Pass AdManager to HomeScreen
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}