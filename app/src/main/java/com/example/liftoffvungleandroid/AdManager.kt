package com.example.liftoffvungleandroid
import android.content.Context
import android.util.Log
import com.vungle.ads.AdConfig
import com.vungle.ads.BaseAd
import com.vungle.ads.InterstitialAd
import com.vungle.ads.InterstitialAdListener
import com.vungle.ads.RewardedAd
import com.vungle.ads.VungleBannerView
import com.vungle.ads.VungleError

//Ad Manager Class to load and show ad placements
class AdManager(private val context: Context) : InterstitialAdListener{
    private val TAG = "VungleAdManager" //Tag for logging

    //Declare Ad variables
    private var interstitialAd: InterstitialAd? = null
    private var rewardedVideoAd: RewardedAd? = null
    private var bannerAd: VungleBannerView? = null //'BannerAd' is deprecated. Use VungleBannerView instead

    //Placement ID variables
    private val interstitialPlacementId = "DEFAULT-0328593"
    //private val rewardedVideoPlacementId = "67b80530def9e46cb8d322aa"

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


}