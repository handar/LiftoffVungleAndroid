package com.example.liftoffvungleandroid
import android.content.Context
import android.util.Log
import android.widget.FrameLayout
import com.vungle.ads.AdConfig
import com.vungle.ads.BannerAdListener
import com.vungle.ads.BaseAd
import com.vungle.ads.InterstitialAd
import com.vungle.ads.InterstitialAdListener
import com.vungle.ads.RewardedAd
import com.vungle.ads.RewardedAdListener
import com.vungle.ads.VungleAdSize
import com.vungle.ads.VungleBannerView
import com.vungle.ads.VungleError

//Ad Manager Class to load and show ad placements
class AdManager(private val context: Context) : InterstitialAdListener, RewardedAdListener,
    BannerAdListener {
    private val TAG = "VungleAdManager" //Tag for logging

    //Declare Ad variables
    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null
    private var bannerAd: VungleBannerView? = null //'BannerAd' is deprecated. Use VungleBannerView instead

    //Placement ID variables
    private val interstitialPlacementId = "DEFAULT-0328593"
    private val rewardedVideoPlacementId = "REWARDED_VIDEO_DEFAULT_NON_BIDDING-8683118"
     val bannerPlacementId = "BANNER_DEFAULT_NON_BIDDING-6185409"
    private val mrecPlacementId = "MREC_DEFAULT_NON_BIDDING-5747326"

    private var bannerAdContainer: FrameLayout? = null  // Jetpack compose does not include RelativeLayout or FrameLayout. My dynamic "bannerAdContainer".


    //==================================================================================================================

    //Interstitial handling
    fun createAndLoadInterstitial() {
        interstitialAd = InterstitialAd(context, interstitialPlacementId, AdConfig().apply {
        }).apply {
            adListener = this@AdManager
            load()
        }
    }

    fun playInterstitial(){
        if (interstitialAd?.canPlayAd() == true) {
            interstitialAd?.play()
        } else{
            Log.d(TAG, "Interstitial is not ready to play")
        }

    }

    //InterstitialAdListener callback methods
    override fun onAdClicked(baseAd: BaseAd) {
        Log.d(TAG, "Ad Click Recorded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log ad click for placement and creative ID
    }

    override fun onAdEnd(baseAd: BaseAd) {
        Log.d(TAG, "Ad Ended for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log ad end for placement and creative ID
    }

    override fun onAdFailedToLoad(baseAd: BaseAd, adError: VungleError) {
        Log.d(TAG, "Ad Failed to Load for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}. Error: ${adError.localizedMessage}") //Log ad failed to load for placement and creative ID
    }

    override fun onAdFailedToPlay(baseAd: BaseAd, adError: VungleError) {
        Log.d(TAG, "Ad Failed to Play for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}. Error: ${adError.localizedMessage}") //Log ad failed to play for placement and creative ID
    }

    override fun onAdImpression(baseAd: BaseAd) {
        Log.d(TAG, "Ad Impression Recorded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log impression for placement and creative ID
    }

    override fun onAdLeftApplication(baseAd: BaseAd) {
        Log.d(TAG, "User Clicked and Left app for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log user clicking and leaving app for placement and creative ID
    }

    override fun onAdLoaded(baseAd: BaseAd) {
        Log.d(TAG, "Ad Loaded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log ad load for placement and creative ID
        }

    override fun onAdStart(baseAd: BaseAd) {
        Log.d(TAG, "Ad Started for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log ad start for placement and creative ID
    }

    //==================================================================================================================

    //Rewarded Video Handling
    fun createAndLoadRewardedVideo() {
        rewardedAd = RewardedAd(context, rewardedVideoPlacementId, AdConfig().apply {
        }).apply {
            adListener = this@AdManager
            load()
        }
    }

    fun playRewardedVideo() {
        if (rewardedAd?.canPlayAd() == true) {
            rewardedAd?.play()
        } else {
            Log.d(TAG, "Rewarded Video is not ready to play")
        }
    }

    //RewardedAdListener callback methods
    override fun onAdRewarded(baseAd: BaseAd) {
        Log.d(TAG, "Ad Rewarded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}") //Log ad reward for placement and creative ID
    }

    //==================================================================================================================

    //Banner Ad Handling
    fun createAndLoadBanner() {
        bannerAdContainer = FrameLayout(context) // Create the banner container upon loading it

        //Load the banner
        bannerAd = VungleBannerView(context, bannerPlacementId, VungleAdSize.BANNER).apply {
            adListener = this@AdManager
            load()
        }
        Log.d(TAG, "Banner ad is loading")
    }

    fun playBanner(): FrameLayout? { //returns the bannerAdContainer for us to use
        //Banner has loaded and is ready to play. Show the loaded banner inside of the banner container we created.
        return bannerAdContainer?.apply {
            bannerAd?.let { ad -> //Check if Banner ad is not null and run the code inside the block if not null
                // Ensure VungleBannerView is inside a new FrameLayout before adding it

                val params = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )


                this.addView(ad,params) //Adding bannerAd to the bannerAdContainer
                Log.d(TAG, "Banner ad added to container")
            }
        }
    }

    fun dismissBanner(){
        bannerAdContainer?.removeAllViews()
        bannerAd?.finishAd()
        bannerAd?.adListener =null
        bannerAd = null

        Log.d(TAG, "Banner ad dismissed from container")
    }


}