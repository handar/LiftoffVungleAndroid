import android.util.Log
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.liftoffvungleandroid.AdManager

private val TAG = "VungleAdManager" //Tag for logging

@Composable
fun HomeScreen(
    adManager: AdManager, //these are parameters
    modifier: Modifier = Modifier
) {
    //Observing Banner Ad state, any changes to bannerAdState will trigger recomposition of the UI, so it gets redrawn
    val bannerAd by adManager.bannerAdState.collectAsState()
    val bannerAdMREC by adManager.bannerAdStateMREC.collectAsState()
    val inlineAd by adManager.inlineAdState.collectAsState()
    val nativeAd by adManager.nativeAdState.collectAsState()

    Column(
        modifier = modifier
            .padding(16.dp) //Add padding to the column
            .fillMaxSize() //Fill the available space
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround, //Space evenly
        horizontalAlignment = Alignment.CenterHorizontally //Center items horizontally
    ) {
        //Show the MREC Banner Ad when available
        bannerAdMREC?.let { ad -> //Check if ad is not null, I'm referencing val bannerAdMREC to observe it
            AndroidView(factory = { context -> //Use AndroidView to display non-composable views
                FrameLayout(context).apply {
                    val params = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                    )
                    addView(ad, params)
                }
            })
        } ?: Text(text = "MREC Banner Placeholder", color = Color.White)

        Text(text = "Welcome to My App!")

        //Create and Load an Interstitial Ad
        Button(onClick = {
            Log.d(TAG, "Load Interstitial clicked!")
            adManager.createAndLoadInterstitial()
        }) {
            Text(text = "Load Interstitial")
        }

        //Play an Interstitial Ad once it's loaded
        Button(onClick = {
            Log.d(TAG, "Show Interstitial clicked!")
            adManager.playInterstitial()
        }) {
            Text(text = "Show Interstitial")
        }

        //Create and Load an AppOpen Ad
        Button(onClick = {
            Log.d(TAG, "Load AppOpen clicked!")
            adManager.loadAppOpen()
        }) {
            Text(text = "Load App Open")
        }

        //Play an AppOpen Ad once it's loaded
        Button(onClick = {
            Log.d(TAG, "Show AppOpen clicked!")
            adManager.playAppOpen()
        }) {
            Text(text = "Show AppOpen")
        }

        //Create and Load a Rewarded Video Ad
        Button(onClick = {
            Log.d(TAG, "Load Rewarded Video clicked!")
            adManager.createAndLoadRewardedVideo()
        }) {
            Text(text = "Load Rewarded Video")
        }

        //Play a Rewarded Video Ad once it's loaded
        Button(onClick = {
            Log.d(TAG, "Show Rewarded Video clicked!")
            adManager.playRewardedVideo()
        }) {
            Text(text = "Show Rewarded Video")
        }

        //LoadBanner Ad once it's loaded
        Button(onClick = {
            Log.d(TAG, "Load Banner clicked!")
            adManager.loadBanner()
        }) {
            Text(text = "Load Banner")
        }

        //Dismiss and reload a Banner Ad once it's loaded
        Button(onClick = {
            Log.d(TAG, "Dismiss Banner clicked!")
            adManager.dismissBanner()
        }) {
            Text(text = "Dismiss Banner")
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Inline Ad
        //Show the Inline Ad when available
        inlineAd?.let { ad ->
            AndroidView(factory = { context ->
                FrameLayout(context).apply {
                    val params = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                    )
                    addView(ad, params)
                }
            })
        } ?: Text(text = "Inline Placeholder", color = Color.White)

        //Native ad
        //Show the Native Ad when available
//        nativeAd?.let { ad ->
//            val layout = LayoutNativeAdBinding.inflate(LayoutInflater.from(requireContext()))
//
//            }
//        } ?: Text(text = "Inline Placeholder", color = Color.White)



        Spacer(modifier = Modifier.height(20.dp))

        //Show the Banner Ad when available
        bannerAd?.let { ad ->
            AndroidView(factory = { context ->
                FrameLayout(context).apply {
                    val params = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
                    )
                    addView(ad, params)
                }
            })
        } ?: Text(text = "Banner Placeholder", color = Color.White)
    }
}



