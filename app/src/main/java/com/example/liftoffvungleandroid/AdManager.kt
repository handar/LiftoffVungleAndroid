package com.example.liftoffvungleandroid

import android.content.Context
import android.util.Log
import com.vungle.ads.AdConfig
import com.vungle.ads.BannerAdListener
import com.vungle.ads.BaseAd
import com.vungle.ads.InitializationListener
import com.vungle.ads.InterstitialAd
import com.vungle.ads.InterstitialAdListener
import com.vungle.ads.NativeAd

import com.vungle.ads.RewardedAd
import com.vungle.ads.RewardedAdListener
import com.vungle.ads.VungleAdSize
import com.vungle.ads.VungleAds
import com.vungle.ads.VungleBannerView
import com.vungle.ads.VungleError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.annotation.Native

//Ad Manager Class to load and show ad placements
class AdManager(private val context: Context) : InterstitialAdListener, RewardedAdListener,
    BannerAdListener {
    private val TAG = "VungleAdManager" //Tag for logging

    //Declare Ad variables
    private var interstitialAd: InterstitialAd? = null
    private var appOpenAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null

    private val _bannerAdState = //read and write for AdManager only
        MutableStateFlow<VungleBannerView?>(null) //Observe VungleBannerView status
    val bannerAdState = _bannerAdState.asStateFlow() //Expose bannerAdState as a StateFlow for observing changes. Read only

    private val _bannerAdStateMREC =
        MutableStateFlow<VungleBannerView?>(null) //Observe VungleBannerView status
    val bannerAdStateMREC = _bannerAdStateMREC.asStateFlow()

    private val _inlineAdState = MutableStateFlow<VungleBannerView?>(null)
    val inlineAdState = _inlineAdState.asStateFlow()
    val vngAdSize = VungleAdSize.getAdSizeWithWidthAndHeight(300, 400)

    private var nativeAd: NativeAd? = null
    private val _nativeAdState = MutableStateFlow<NativeAd?>(null) // Native Ad state
    val nativeAdState = _nativeAdState.asStateFlow() // Expose as read-only


    //Placement ID variables
    private val interstitialPlacementId = "DEFAULT-0328593"
    private val rewardedVideoPlacementId = "REWARDED_VIDEO_DEFAULT_NON_BIDDING-8683118"
    private val bannerPlacementId = "BANNER_DEFAULT_NON_BIDDING-6185409"
    private val mrecPlacementId = "MREC_DEFAULT_NON_BIDDING-5747326"
    private val inlinePlacementId = "INLINE_DEFAULT_NONBIDDING-9579149"
    private val appopenPlacementId = "APPOPEN_DEFAULT_NON_BIDDING-6319646"
    private val nativePlacementId = "NATIVE_DEFAULT_NON_BIDDING-0129464"

    //Callback methods
    override fun onAdClicked(baseAd: BaseAd) {
        Log.d(
            TAG,
            "Ad Click Recorded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log ad click for placement and creative ID
    }

    override fun onAdEnd(baseAd: BaseAd) {
        Log.d(
            TAG,
            "Ad Ended for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log ad end for placement and creative ID

        if (baseAd.placementId == bannerPlacementId) {
            Log.d(TAG, "Dismissing banner onAdEnd")
            dismissBanner()
            //loadBanner()
        }else {
            Log.d(TAG, "Dismiss banner failed onAdEnd")
        }
    }

    override fun onAdFailedToLoad(baseAd: BaseAd, adError: VungleError) {
        Log.d(
            TAG,
            "Ad Failed to Load for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}. Error: ${adError.localizedMessage}"
        ) //Log ad failed to load for placement and creative ID
    }

    override fun onAdFailedToPlay(baseAd: BaseAd, adError: VungleError) {
        Log.d(
            TAG,
            "Ad Failed to Play for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}. Error: ${adError.localizedMessage}"
        ) //Log ad failed to play for placement and creative ID
    }

    override fun onAdImpression(baseAd: BaseAd) {
        Log.d(
            TAG,
            "Ad Impression Recorded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log impression for placement and creative ID

    }

    override fun onAdLeftApplication(baseAd: BaseAd) {
        Log.d(
            TAG,
            "User Clicked and Left app for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log user clicking and leaving app for placement and creative ID
    }

    override fun onAdLoaded(baseAd: BaseAd) {
        Log.d(
            TAG, "Ad Loaded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log ad load for placement and creative ID

        //This is how you can show ads automatically
        if (baseAd == appOpenAd && appOpenAd?.canPlayAd() == true) {
            Log.d(TAG, "App Open Ad is ready, playing now")
            appOpenAd?.play()
        } else {
            Log.d(TAG, "App Open Ad is not ready yet")
        }

        if (baseAd == interstitialAd && interstitialAd?.canPlayAd() == true) {
            Log.d(TAG, "Interstitial Ad is ready, playing now")
            interstitialAd?.play()
        }else {
            Log.d(TAG, "Interstitial is not ready yet")
        }


    }

    override fun onAdStart(baseAd: BaseAd) {
        Log.d(
            TAG,
            "Ad Started for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log ad start for placement and creative ID
    }

    override fun onAdRewarded(baseAd: BaseAd) {
        Log.d(
            TAG,
            "Ad Rewarded for Placement ID: ${baseAd.placementId} / Creative ID: ${baseAd.creativeId}"
        ) //Log ad reward for placement and creative ID
    }

    //Interstitial handling
    fun createAndLoadInterstitial() {
        interstitialAd = InterstitialAd(context, interstitialPlacementId, AdConfig()).apply {
            adListener = this@AdManager
            load()
        }
    }

    fun playInterstitial() {
        if (interstitialAd?.canPlayAd() == true) {
            interstitialAd?.play()
        } else {
            Log.d(TAG, "Interstitial is not ready to play")
        }

    }

    //App Open Handling
    fun loadAppOpen(){
        appOpenAd = InterstitialAd(context, appopenPlacementId, AdConfig()).apply {
            adListener = this@AdManager
            load()
        }
    }

    fun playAppOpen() {
        if (appOpenAd?.canPlayAd() == true) {
            appOpenAd?.play()
        } else {
            Log.d(TAG, "App Open is not ready to play")
        }
    }

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

    //Banner Ad Handling
    fun loadBanner() {
        Log.d(TAG, "Banner ad is loading")
        _bannerAdState.value = VungleBannerView(context, bannerPlacementId, VungleAdSize.BANNER).apply { //Only AdManager can write to _bannerAdState
            adListener = this@AdManager
            load()
        }

        Log.d(TAG, "MREC Banner ad is loading")
        _bannerAdStateMREC.value = VungleBannerView(
            context, mrecPlacementId,
            VungleAdSize.MREC
        ).apply {
            adListener = this@AdManager
            load()
        }
    }

    fun dismissBanner() {
        _bannerAdState.value?.let {
            it.finishAd()
            it.adListener = null
            Log.d(TAG, "Banner ad removed listener")
        }
        _bannerAdState.value = null
        Log.d(TAG, "Banner ad dismissed from container")

        _bannerAdStateMREC.value?.let {
            it.finishAd()
            it.adListener = null
            Log.d(TAG, "MREC Banner ad removed listener")
        }
        _bannerAdStateMREC.value = null
        Log.d(TAG, "MREC Banner ad dismissed from container")
    }

    //Inline Ad Handling
    fun loadInline() {
        Log.d(TAG, "Inline ad is loading")
        _inlineAdState.value = VungleBannerView(context, inlinePlacementId, vngAdSize).apply {
            adListener = this@AdManager
            load()
        }
    }

    fun dismissInline() {
        _inlineAdState.value?.let {
            it.finishAd()
            it.adListener = null
            Log.d(TAG, "Inline ad removed listener")
        }
    }

    //Native Ad Handling
    fun loadNative() {
        nativeAd = NativeAd(context, nativePlacementId).apply {
            adListener = this@AdManager
            load()
        }
    }





}