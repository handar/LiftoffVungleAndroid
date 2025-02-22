import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    //Lambda for each button click
    onLoadInterstitialClicked: () -> Unit = {},
    onShowInterstitialClicked: () -> Unit = {},

    onLoadRewardedAdClicked: () -> Unit = {},
    onShowRewardedAdClicked: () -> Unit = {},

    onLoadBannerClicked: () -> Unit = {},
    onShowBannerClicked: () -> FrameLayout? = {null}, // Get the banner frameLayout
    onDismissBannerClicked: () -> Unit = {},

    onLoadMRECClicked: () -> Unit = {},
    onShowMRECClicked: () -> Unit = {}
) {
    val bannerFrameLayout = remember { mutableStateOf<FrameLayout?>(null) }

//    Column(
//        verticalArrangement = Arrangement.SpaceAround
//    ) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Cyan) // Background color for the outer Box
//    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Add padding to the column
                .fillMaxSize(), // Fill the available space
            verticalArrangement = Arrangement.Center, // Center items vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center items horizontally
        ) { //Add padding to the column
            Text(text = "Welcome to My App!")

            //Create and Load an Interstitial Ad
            Button(onClick = onLoadInterstitialClicked) {
                Text(text = "Load Interstitial")
            }

            //Play an Interstitial Ad once it's loaded
            Button(onClick = onShowInterstitialClicked) {
                Text(text = "Show Interstitial")
            }

            //Create and Load a Rewarded Video Ad
            Button(onClick = onLoadRewardedAdClicked) {
                Text(text = "Load Rewarded Video")
            }

            //Play a Rewarded Video Ad once it's loaded
            Button(onClick = onShowRewardedAdClicked) {
                Text(text = "Show Rewarded Video")
            }

            //Create and Load a Banner Ad
            Button(onClick = onLoadBannerClicked) {
                Text(text = "Load Banner")
            }

            //Play a Banner Ad once it's loaded
            Button(onClick = {
                bannerFrameLayout.value = onShowBannerClicked()
                }
            ) {
                Text(text = "Show Banner")
            }

            //Dismiss a Banner Ad once it's loaded
            Button(onClick = {
                bannerFrameLayout.value = null //Remove from UI
                onDismissBannerClicked() //Call AdManager to remove banner
            }) {
                Text(text = "Dismiss Banner")
            }

            //Create and Load a MREC Banner Ad
            Button(onClick = onLoadBannerClicked) {
                Text(text = "Load MREC")
            }

//            //Play a MCREC Banner Ad once it's loaded
//            Button(onClick = onShowBannerClicked) {
//                Text(text = "Show MREC")
//            }
            Spacer(modifier = Modifier.height(20.dp))

            //Show the Banner Ad when available
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .background(Color.Blue), //Default blue background before ad loads
                contentAlignment = Alignment.Center
            ) {
                bannerFrameLayout.value?.let { frameLayout ->
                    AndroidView(factory = { frameLayout }, modifier = Modifier.align(Alignment.Center))
                } ?: Text(text = "Banner Placeholder", color = Color.White)
            }
        }
        // Add a bottom container
//        val bannerAdContainer = Box( // Renamed to bannerAdContainer
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//                .background(Color.LightGray) // Background color for the bottom container
//                .align(Alignment.BottomCenter)
//        ){
//            Text(text = "Bottom Container", modifier = Modifier.align(Alignment.Center))
//        }


//        AndroidView(
//            factory = { context ->
//                RelativeLayout(context).apply {
//                    val bannerAdContainer = FrameLayout(context).apply{
//                        id = 12345 // Unique ID for banner container
//
//
//                    }
//
//
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//        )
        //}
//    }
}


