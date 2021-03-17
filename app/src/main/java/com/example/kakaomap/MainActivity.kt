package com.example.kakaomap

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import com.example.kakaomap.databinding.ActivityMainBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        markOnMap()
        setMarker()
        getHashKey()
    }

    fun getHashKey() {
        var packageInfo: PackageInfo = PackageInfo()
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        for (signature: Signature in packageInfo.signatures) {
            try {
                var md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KEY_HASH", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KEY_HASH", "Unable to get MessageDigest.signature = " + signature, e)
            }

        }
    }

    fun markOnMap() {
        mapView = MapView(this)
        val mapViewContainer = binding.mapView
        mapViewContainer.addView(mapView)
    }

    fun setMarker() {
        val marker = MapPOIItem()
        marker.itemName = "주예"
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(37.5514579595, 126.951949155)
        marker.markerType = MapPOIItem.MarkerType.YellowPin
        mapView.addPOIItem(marker)
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5514579595, 126.951949155),true)
    }

}