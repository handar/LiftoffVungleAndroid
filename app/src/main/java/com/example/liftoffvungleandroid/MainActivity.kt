package com.example.liftoffvungleandroid

import HomeScreen
//import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.liftoffvungleandroid.ui.theme.LiftoffVungleAndroidTheme
import com.vungle.ads.InitializationListener
import com.vungle.ads.VungleAds
import com.vungle.ads.VungleError

class MainActivity : ComponentActivity() {
    private val TAG = "VungleAdManager" //Tag for logging
    private lateinit var adManager: AdManager // Instance of AdManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize Vungle SDK
        VungleAds.init(this, "67b80530def9e46cb8d322aa", object : InitializationListener {
            override fun onSuccess() {
                Log.d(TAG, "Vungle SDK init onSuccess()")
            }
            override fun onError(vungleError: VungleError) {
                Log.d(TAG, "onError(): ${vungleError.localizedMessage}")
            }
        })

        // Initialize AdManager
        adManager = AdManager(this)

        enableEdgeToEdge()
        setContent {
            LiftoffVungleAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen( //Pass the lambda functions to the HomeScreen and load the view
                        onLoadInterstitialClicked = {
                            Log.d(TAG, "Load Interstitial clicked!")
                            adManager.createAndLoadInterstitial()
                        },
                        onShowInterstitialClicked = {
                            Log.d(TAG, "Show Interstitial clicked!")
                            adManager.playInterstitial()
                        },
                        onLoadRewardedAdClicked = {
                            Log.d(TAG, "Load Rewarded Video clicked!")
                            adManager.createAndLoadRewardedVideo()
                        },
                        onShowRewardedAdClicked = {
                            Log.d(TAG, "Show Rewarded Video clicked!")
                            adManager.playRewardedVideo()
                        },
                        onLoadBannerClicked = {
                            Log.d(TAG, "Load Banner clicked!")
                            adManager.createAndLoadBanner()
                        },
                        onShowBannerClicked = {
                            Log.d(TAG, "Show Banner clicked!")
                            adManager.playBanner()
                        },
                        onDismissBannerClicked = {
                            Log.d(TAG, "Dismiss Banner clicked!")
                            adManager.dismissBanner()
                        },
                        onLoadMRECClicked = {
                            Log.d(TAG, "Load MREC clicked!")
                            // Add your code to load the MREC here
                        },
                        onShowMRECClicked = {
                            Log.d(TAG, "Show MREC clicked!")
                            // Add your code to show the MREC here
                        }

                    )

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LiftoffVungleAndroidTheme {
        Greeting("Android")
    }
}