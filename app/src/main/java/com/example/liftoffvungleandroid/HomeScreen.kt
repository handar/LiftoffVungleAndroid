import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun HomeScreen(
    //Lambda for each button click
    onLoadInterstitialClicked: () -> Unit = {},
    onShowInterstitialClicked: () -> Unit = {},

    onLoadRewardedAdClicked: () -> Unit = {},
    onShowRewardedAdClicked: () -> Unit = {},

    onLoadBannerClicked: () -> Unit = {},
    onShowBannerClicked: () -> Unit = {},

    onLoadMRECClicked: () -> Unit = {},
    onShowMRECClicked: () -> Unit = {}
) {
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
        Button(onClick = onShowInterstitialClicked) {
            Text(text = "Show Rewarded Video")
        }

        //Create and Load a Banner Ad
        Button(onClick = onLoadBannerClicked) {
            Text(text = "Load Banner")
        }

        //Play a Banner Ad once it's loaded
        Button(onClick = onShowBannerClicked) {
            Text(text = "Show Banner")
        }

        //Create and Load a MREC Banner Ad
        Button(onClick = onLoadBannerClicked) {
            Text(text = "Load MREC")
        }

        //Play a MCREC Banner Ad once it's loaded
        Button(onClick = onShowBannerClicked) {
            Text(text = "Show MREC")
        }


    }
}


