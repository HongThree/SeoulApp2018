package com.example.kihunahn.seoulapp2018.Fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.NMap.NMapFragment
import com.example.kihunahn.seoulapp2018.NMap.NMapViewerResourceProvider
import com.example.kihunahn.seoulapp2018.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
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

    var mGoogleApiClient: GoogleApiClient? = null
    private val BROADCAST_ACTION = "android.location.PROVIDERS_CHANGED"
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

    override fun onResume() {
        super.onResume()
        activity!!.registerReceiver(gpsLocationReceiver, IntentFilter(BROADCAST_ACTION))
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

        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient!!.connect()

        showSettingDialog()

        return v
    }

    private fun showSettingDialog() {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //Setting priotity of Location request to high
        locationRequest.interval = 1 * 1000
        locationRequest.fastestInterval = 5 * 1000//5 sec Time interval for location update
        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true) //this is the key ingredient to show dialog always when GPS is off

        val result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build())
        result.setResultCallback(ResultCallback<LocationSettingsResult> {result ->
            var status = result.status
            var state = result.locationSettingsStates
            when(status.statusCode){
                LocationSettingsStatusCodes.SUCCESS->{

                }
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED->{

                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE->{

                }
            }

        })
    }

    private val sendUpdatesToUI = Runnable { showSettingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGoogleApiClient = GoogleApiClient.Builder(activity!!)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient!!.connect()
        showSettingDialog()
    }

    private val gpsLocationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("tlqkf",intent!!.action)
            if (intent!!.action == BROADCAST_ACTION) {
                var locationManager:LocationManager = context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                Log.d("ASD","wlsdlq")
                //Check if GPS is turned ON or OFF
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Log.e("About GPS", "GPS is Enabled in your device")
                } else {
                    Handler().postDelayed(sendUpdatesToUI,10)
                    Log.e("About GPS", "GPS is Disabled in your device")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showSettingDialog()
        mapView?.setBuiltInZoomControls(true, null)
        mapView?.setOnMapStateChangeListener(this)
        mapController = mapView?.mapController
        mapViewerResourceProvider = NMapViewerResourceProvider(activity)
        mapOverlayManager = NMapOverlayManager(activity!!, mapView, mapViewerResourceProvider)
        moveMapCenter()
    }

    private val onMyLocationChangeListener = object : NMapLocationManager.OnLocationChangeListener {
        override fun onLocationChanged(locationManager: NMapLocationManager, myLocation: NGeoPoint): Boolean {
            Log.d("QWE", "wlsdlq")
            if (mapController != null) {
                mapController!!.animateTo(myLocation)

            }
            var lati = myLocation.getLatitude()
            var loti = myLocation.getLongitude()
            dlati.add(lati)
            dloti.add(loti)
            //val currentPoint = NGeoPoint(lati, loti)
            //mapController!!.mapCenter = currentPoint
            mapController!!.setZoomEnabled(true)
            mapController!!.zoomLevel = 100
            var len = dlati.size - 1
            val pathData = NMapPathData(len)
            pathData.initPathData()
            for (i in 0..len) {
                Log.d("ASD", dlati[i].toString() + " " + dloti[i].toString())
                if (i == 0)
                    pathData.addPathPoint(dlati[i], dloti[i], NMapPathLineStyle.TYPE_SOLID)
                else
                    pathData.addPathPoint(dlati[i], dloti[i], 0)
            }
            pathData.endPathData()
            val pathDataOverlay = mapOverlayManager!!.createPathDataOverlay(pathData)
            pathDataOverlay.setLineColor(Color.RED, 1000)
            //drawline()
            return true
        }

        override fun onLocationUpdateTimeout(locationManager: NMapLocationManager) {}

        override fun onLocationUnavailableArea(locationManager: NMapLocationManager, myLocation: NGeoPoint) {

        }
    }

    private fun moveMapCenter() {
        mMapLocationManager = NMapLocationManager(activity!!)
        mMapLocationManager?.setOnLocationChangeListener(onMyLocationChangeListener)
        mMapLocationManager!!.enableMyLocation(true)
        mMyLocationOverlay = mapOverlayManager!!.createMyLocationOverlay(mMapLocationManager, mMapCompassManager)
    }

    private fun drawline() {
        if (dlati.size > 0) {
            var len = dlati.size - 1
            val currentPoint = NGeoPoint(dlati[len], dloti[len])
            mapController!!.mapCenter = currentPoint
            mapController!!.setZoomEnabled(true)
            mapController!!.zoomLevel = 100

            val pathData = NMapPathData(len)
            pathData.initPathData()
            for (i in 0..len) {
                Log.d("ASD", dlati[i].toString() + " " + dloti[i].toString())
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

