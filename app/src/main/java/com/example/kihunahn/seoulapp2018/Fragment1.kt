package com.example.kihunahn.seoulapp2018

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.NMap.NMapFragment
import com.example.kihunahn.seoulapp2018.NMap.NMapPOIflagType
import com.example.kihunahn.seoulapp2018.NMap.NMapViewerResourceProvider
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


class Fragment1 : NMapFragment(), NMapView.OnMapStateChangeListener, NMapPOIdataOverlay.OnStateChangeListener {
    var mapView: NMapView? = null
    var mapController: NMapController? = null
    var mapViewerResourceProvider: NMapViewerResourceProvider? = null
    var mapOverlayManager: NMapOverlayManager? = null
    var lati:ArrayList<String> = ArrayList()
    var loti:ArrayList<String> = ArrayList()
    var dlati:ArrayList<Double> = ArrayList()
    var dloti:ArrayList<Double> = ArrayList()

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
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lati = arguments?.getStringArrayList("la")!!
        loti = arguments?.getStringArrayList("lo")!!
        var len = lati.size-1
        for (i in 0..len) {
            dlati.add(java.lang.Double.parseDouble(lati[i]))
            dloti.add(java.lang.Double.parseDouble(loti[i]))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mapView?.setBuiltInZoomControls(true,null)
        mapView?.setOnMapStateChangeListener(this)
        mapController = mapView?.mapController
        mapViewerResourceProvider = NMapViewerResourceProvider(activity)
        mapOverlayManager = NMapOverlayManager(activity!!, mapView, mapViewerResourceProvider)
        moveMapCenter()
        //mMapContext?.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun moveMapCenter() {
        if(dlati.size>0) {
            var len = dlati.size-1
            val currentPoint = NGeoPoint(dlati[0], dloti[0])
            mapController!!.mapCenter = currentPoint
            val poiData = NMapPOIdata(2, mapViewerResourceProvider)
            poiData.addPOIitem(dlati[0], dloti[0], "현재 위치", NMapPOIflagType.FROM, 0)
            poiData.addPOIitem(dlati[len], dloti[len], "도착 위치", NMapPOIflagType.TO, 0)
            poiData.endPOIdata()
            val poiDataOverlay = mapOverlayManager!!.createPOIdataOverlay(poiData, null)
            poiDataOverlay.showAllPOIdata(0)
            poiDataOverlay.onStateChangeListener = this

            val pathData = NMapPathData(len)
            pathData.initPathData()
            for(i in 0..len) {
                if(i==0)
                    pathData.addPathPoint(dlati[i], dloti[i], NMapPathLineStyle.TYPE_SOLID)
                else
                    pathData.addPathPoint(dlati[i], dloti[i], 0)
                /*
                pathData.addPathPoint(127.108099, 37.366034, NMapPathLineStyle.TYPE_SOLID)
                pathData.addPathPoint(127.108088, 37.366043, 0)
                pathData.addPathPoint(127.108079, 37.365619, 0)
                pathData.addPathPoint(127.107458, 37.365608, 0)
                pathData.addPathPoint(127.107232, 37.365608, 0)
                pathData.addPathPoint(127.106904, 37.365624, 0)
                pathData.addPathPoint(127.105933, 37.365621, 0)
                pathData.addPathPoint(127.105929, 37.366378, 0)
                pathData.addPathPoint(127.106279, 37.366380, 0)*/
            }
            pathData.endPathData()
            val pathDataOverlay = mapOverlayManager!!.createPathDataOverlay(pathData)
        }
    }

    companion object {
        private val CLIENT_ID = "XrPrMsRtFpXGFaq_Az1I"// 애플리케이션 클라이언트 아이디 값
    }
}