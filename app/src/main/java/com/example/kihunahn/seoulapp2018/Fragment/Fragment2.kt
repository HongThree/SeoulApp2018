package com.example.kihunahn.seoulapp2018.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.NMap.NMapFragment
import com.example.kihunahn.seoulapp2018.NMap.NMapViewerResourceProvider
import com.example.kihunahn.seoulapp2018.R
import com.nhn.android.maps.NMapCompassManager
import com.nhn.android.maps.NMapController
import com.nhn.android.maps.NMapLocationManager
import com.nhn.android.maps.NMapView
import com.nhn.android.maps.maplib.NGeoPoint
import com.nhn.android.maps.nmapmodel.NMapError
import com.nhn.android.maps.overlay.NMapPOIitem
import com.nhn.android.maps.overlay.NMapPathData
import com.nhn.android.maps.overlay.NMapPathLineStyle
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay
import com.nhn.android.mapviewer.overlay.NMapOverlayManager
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay


class Fragment2 : NMapFragment(), NMapView.OnMapStateChangeListener, NMapPOIdataOverlay.OnStateChangeListener {
    var mapView: NMapView? = null
    var mapController: NMapController? = null
    var mapViewerResourceProvider: NMapViewerResourceProvider? = null
    var mapOverlayManager: NMapOverlayManager? = null
    var mMapLocationManager: NMapLocationManager? = null
    var mMyLocationOverlay: NMapMyLocationOverlay? = null
    var mMapCompassManager: NMapCompassManager? = null

    var dlati: ArrayList<Double> = ArrayList()
    var dloti: ArrayList<Double> = ArrayList()

    var mLocationManager: LocationManager? = null

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
        val v = inflater.inflate(R.layout.fragment1, container, false)
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
        mapViewerResourceProvider = NMapViewerResourceProvider(activity)
        mapOverlayManager = NMapOverlayManager(activity!!, mapView, mapViewerResourceProvider)
        mLocationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val mLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            var lati:Double = location?.longitude!!
            var loti:Double = location?.latitude
            dlati.add(lati)
            dloti.add(loti)
            drawline()
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }

    }

    private val onMyLocationChangeListener = object : NMapLocationManager.OnLocationChangeListener {
        override fun onLocationChanged(locationManager: NMapLocationManager, myLocation: NGeoPoint): Boolean {
            if (mapController != null) {
                mapController!!.animateTo(myLocation)
//                var lati = myLocation.getLongitude()
//                var loti = myLocation.getLatitude()
//                if (dlati.size == 0) {
//                    dlati.add(lati)
//                    dloti.add(loti)
//                }
//                if (dlati.size > 0) {
//                    var tx: Double = dlati[dlati.size - 1]
//                    var ty: Double = dloti[dloti.size - 1]
//                    Log.d("ASD", tx.toString() + " " + ty.toString())
//
//                }
//                dlati.add(lati)
//                dloti.add(loti)
//                drawline()
            }
            return true
        }

        override fun onLocationUpdateTimeout(locationManager: NMapLocationManager) {

        }

        override fun onLocationUnavailableArea(locationManager: NMapLocationManager, myLocation: NGeoPoint) {
            Toast.makeText(activity!!, "QWEQWE", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun moveMapCenter() {
        var sizel: Float = 1.0F
        mLocationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, sizel, mLocationListener)
        mLocationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, sizel, mLocationListener)
        mMapLocationManager = NMapLocationManager(activity!!)
        mMapLocationManager?.setOnLocationChangeListener(onMyLocationChangeListener)
        mMapLocationManager!!.enableMyLocation(true)
        mMyLocationOverlay = mapOverlayManager!!.createMyLocationOverlay(mMapLocationManager, mMapCompassManager)
    }

    private fun drawline() {
        Log.d("QWE", "draw")
        if (dlati.size > 0) {
            var len = dlati.size - 1
            val currentPoint = NGeoPoint(dlati[len], dloti[len])
            val pathData = NMapPathData(len)
            mapController!!.mapCenter = currentPoint
            mapController!!.setZoomEnabled(true)
            mapController!!.zoomLevel = 50
            pathData.initPathData()
            for (i in 0..len) {
                Log.d("QWE", dlati[i].toString() + " " + dloti[i].toString())
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

    companion object {
        private val CLIENT_ID = "XrPrMsRtFpXGFaq_Az1I"// 애플리케이션 클라이언트 아이디 값
    }
}