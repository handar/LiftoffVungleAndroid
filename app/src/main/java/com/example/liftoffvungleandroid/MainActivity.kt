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
        val adManager = AdManager(this) //adManager is an instance of AdManager

        //Initialize Vungle S
        VungleAds.init(this, appID, object : InitializationListener {
            override fun onSuccess() {
                Log.d(TAG, "Vungle SDK init onSuccess()")
                adManager.loadAppOpen()
                adManager.loadBanner() //load banners after Vungle SDK is initialized
                adManager.loadInline() //load inline after Vungle SDK is initialized
            }
            override fun onError(vungleError: VungleError) {
                Log.d(TAG, "onError(): ${vungleError.localizedMessage}")
            }
        })

        //Init Vungle when I'm ready
        //Initialize Vungle when needed
        //adManager.initializeVungle()

        enableEdgeToEdge()
        setContent {
            LiftoffVungleAndroidTheme { // 1️⃣ Applies the app’s theme
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // 2️⃣ Creates a scaffold layout
                    HomeScreen( // 3️⃣ Loads the main UI screen
                        adManager, // 4️⃣ Passes the AdManager instance to HomeScreen
                        //By passing AdManager to HomeScreen, the UI screen
                        //can request and display ads properly. The HomeScreen
                        //has the buttons that will load and show an ad.
                        Modifier.padding(innerPadding) // 5️⃣ Adjusts padding for system bars
                    )
                }
            }
        }
    }
}