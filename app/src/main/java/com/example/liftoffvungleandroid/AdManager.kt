package com.example.liftoffvungleandroid
import android.content.Context
import com.vungle.ads.InterstitialAd
import com.vungle.ads.RewardedAd
import com.vungle.ads.VungleBannerView

//Ad Manager Class to load and show ad placements
class AdManager(private val context: Context) {
    //Declare Ad variables
    private var interstitialAd: InterstitialAd? = null
    private var rewardedVideoAd: RewardedAd? = null
    private var bannerAd: VungleBannerView? = null //'BannerAd' is deprecated. Use VungleBannerView instead



}