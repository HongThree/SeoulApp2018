package com.example.kihunahn.seoulapp2018.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.NMap.NMapFragment
import com.example.kihunahn.seoulapp2018.NMap.NMapPOIflagType
import com.example.kihunahn.seoulapp2018.NMap.NMapViewerResourceProvider
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.Server.Companion.stamplst
import com.nhn.android.maps.NMapController
import com.nhn.android.maps.NMapView
import com.nhn.android.maps.maplib.NGeoPoint
import com.nhn.android.maps.nmapmodel.NMapError
import com.nhn.android.maps.overlay.NMapPOIdata
import com.nhn.android.maps.overlay.NMapPOIitem
import com.nhn.android.maps.overlay.NMapPathData
import com.nhn.android.maps.overlay.NMapPathLineStyle
import com.nhn.android.mapviewer.overlay.NMapOverlayManager
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay


class Fragment2 : NMapFragment(), NMapView.OnMapStateChangeListener, NMapPOIdataOverlay.OnStateChangeListener {
    var mapView: NMapView? = null
    var mapController: NMapController? = null
    var mapViewerResourceProvider: NMapViewerResourceProvider? = null
    var mapOverlayManager: NMapOverlayManager? = null
    var sLocation: Location? = null

    var dlati: ArrayList<Double> = ArrayList()
    var dloti: ArrayList<Double> = ArrayList()

    var mLocationManager: LocationManager? = null
    var criteria: Criteria? = null

    var s_lst : ArrayList<Boolean> = arrayListOf(false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false,false,false,
            false,false,false,false,false,false,false,false)
    override fun onMapCenterChangeFine(p0: NMapView?) {
    }

    override fun onAnimationStateChange(p0: NMapView?, p1: Int, p2: Int) {

    }

    override fun onMapInitHandler(NMapView: NMapView?, nMapError: NMapError?) {
        if (nMapError == null) {
            moveMapCenter()
        } else {
            Log.e("map init error", nMapError.message)
        }
    }

    override fun onZoomLevelChange(p0: NMapView?, p1: Int) {

    }

    override fun onMapCenterChange(p0: NMapView?, p1: NGeoPoint?) {

    }

    override fun onFocusChanged(p0: NMapPOIdataOverlay?, p1: NMapPOIitem?) {

    }

    override fun onCalloutClick(p0: NMapPOIdataOverlay?, p1: NMapPOIitem?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment2, container, false)
        mapView = (v.findViewById(R.id.mapView) as NMapView)
        mapView!!.setClientId(CLIENT_ID)
        mapView!!.isClickable = true
        mapView!!.isEnabled = true
        mapView!!.isFocusable = true
        mapView!!.isFocusableInTouchMode = true
        mapView!!.requestFocus()
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        mapView?.setBuiltInZoomControls(true, null)
        mapView?.setOnMapStateChangeListener(this)
        mapController = mapView?.mapController
        mapController!!.reload()
        mapController!!.zoomLevel = 50
        mapViewerResourceProvider = NMapViewerResourceProvider(activity)
        mapOverlayManager = NMapOverlayManager(activity!!, mapView, mapViewerResourceProvider)
        mLocationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        criteria = Criteria()
        criteria!!.accuracy = Criteria.ACCURACY_COARSE
        criteria!!.powerRequirement = Criteria.POWER_LOW
        criteria!!.isAltitudeRequired = false
        criteria!!.isBearingRequired = false
        criteria!!.isSpeedRequired = false
        criteria!!.isCostAllowed = true
        moveMapCenter()
    }

    val mLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            if (isBetterLocation(location!!, sLocation)) {
                sLocation = location
                var lati: Double = java.lang.Double.parseDouble(String.format("%.6f", location.longitude))
                var loti: Double = java.lang.Double.parseDouble(String.format("%.6f", location.latitude))
                if (dlati.size == 0) {
                    dlati.add(lati)
                    dloti.add(loti)
                    dlati.add(lati)
                    dloti.add(loti)
                } else {
                    dlati.add(lati)
                    dloti.add(loti)
                }
                drawline()
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

    }
    private val TWO_MINUTES: Long = 1000 * 60 * 2

    fun isBetterLocation(location: Location, currentBestLocation: Location?): Boolean {
        if (currentBestLocation == null) {
            return true
        }

        // Check whether the new location fix is newer or older
        val timeDelta: Long = location.time - currentBestLocation.time
        val isSignificantlyNewer: Boolean = timeDelta > TWO_MINUTES
        val isSignificantlyOlder: Boolean = timeDelta < -TWO_MINUTES

        when {
            isSignificantlyNewer -> return true
            isSignificantlyOlder -> return false
        }

        val isNewer: Boolean = timeDelta > 0L
        val accuracyDelta: Float = location.accuracy - currentBestLocation.accuracy
        val isLessAccurate: Boolean = accuracyDelta > 0f
        val isMoreAccurate: Boolean = accuracyDelta < 0f
        val isSignificantlyLessAccurate: Boolean = accuracyDelta > 200f

        val isFromSameProvider: Boolean = location.provider == currentBestLocation.provider

        return when {
            isMoreAccurate -> true
            isNewer && !isLessAccurate -> true
            isNewer && !isSignificantlyLessAccurate && isFromSameProvider -> true
            else -> false
        }
    }

    @SuppressLint("MissingPermission")
    private fun moveMapCenter() {
        Log.e("move", "move")
        val provider = mLocationManager!!.getBestProvider(criteria, true)
        mLocationManager!!.requestLocationUpdates(provider, 0, 0.0f, mLocationListener)
    }

    fun move() {
        if (dlati.size > 0) {
            val len = dlati.size - 1
            val currentPoint = NGeoPoint(dlati[len], dloti[len])
            mapController!!.mapCenter = currentPoint
            mapController!!.setZoomEnabled(true)
            mapController!!.zoomLevel = 50
        }
    }

    private fun drawline() {
        if (dlati.size > 0) {
            val len = dlati.size - 1
            val currentPoint = NGeoPoint(dlati[len], dloti[len])

            for (i in 0..27) {
                val diffLatitude = LatitudeInDifference(200)
                val diffLongitude = LongitudeInDifference(dloti[len], 200)
                if (dlati[len] - diffLongitude <= location_lati[i] && location_lati[i] <= dlati[len] + diffLongitude) {
                    if (dloti[len] - diffLatitude <= location_loti[i] && location_loti[i] <= dloti[len] + diffLatitude){
                        val poiData = NMapPOIdata(2, mapViewerResourceProvider)
                        poiData.addPOIitem(location_lati[i], location_loti[i], "", NMapPOIflagType.FROM, 0)
                        poiData.endPOIdata()
                        val poiDataOverlay = mapOverlayManager!!.createPOIdataOverlay(poiData, null)
                        poiDataOverlay.showAllPOIdata(0)
                        poiDataOverlay.onStateChangeListener = this

                        if(!stamplst[i]){
                            s_lst[i] = true
                            stamplst[i] = true
                        }
                        break
                    }

                }
            }

            val pathData = NMapPathData(len)
            mapController!!.mapCenter = currentPoint
            mapController!!.setZoomEnabled(true)

            pathData.initPathData()
            for (i in 0..len) {
                if (i == 0)
                    pathData.addPathPoint(dlati[i], dloti[i], NMapPathLineStyle.TYPE_SOLID)
                else
                    pathData.addPathPoint(dlati[i], dloti[i], 0)
            }
            pathData.endPathData()

            val pathDataOverlay = mapOverlayManager!!.createPathDataOverlay(pathData)
            pathDataOverlay.setLineColor(Color.RED, 1000)
        }
    }

    fun LatitudeInDifference(diff: Int): Double {
        var earth = 6371000
        return (diff * 360.0) / (2 * Math.PI * earth)
    }

    fun LongitudeInDifference(lon: Double, diff: Int): Double {
        var earth = 6371000
        return (diff * 360.0) / (2 * Math.PI * earth * Math.cos(Math.toRadians(lon)))
    }

    override fun onPause() {
        Log.e("onPause", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.e("onPause", "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.e("onPause", "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.e("onPause", "onDestroy")
        mLocationManager!!.removeUpdates(mLocationListener)
        dlati.clear()
        dloti.clear()
        super.onDestroy()
    }

    companion object {
        private const val CLIENT_ID = "XrPrMsRtFpXGFaq_Az1I"// 애플리케이션 클라이언트 아이디 값
        var location_lati: DoubleArray = doubleArrayOf(127.047186, 127.083653, 127.084997, 127.084997, 127.103100, 127.099870,
                127.109010, 127.156912, 127.140455, 127.106862, 127.102289, 127.036451, 126.986817,
                126.976589, 126.946757, 126.906542, 126.902020, 126.871578, 126.864751,
                126.856430, 126.902661, 126.914705,
                126.936930, 127.009747, 127.016440, 127.036345, 126.936930, 126.939591, 127.12747)
        var location_loti: DoubleArray = doubleArrayOf(37.689364, 37.668395, 37.620475, 37.620475, 37.578790, 37.553143,
                37.545968, 37.555261, 37.512326, 37.488193, 37.486594, 37.469930, 37.473450,
                37.467788, 37.467546, 37.433892, 37.434226, 37.496575, 37.561721,
                37.563844, 37.585151, 37.635650,
                37.627872, 37.622897, 37.661569, 37.686044, 37.627872, 37.612704, 37.45079)
    }
}