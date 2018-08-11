package com.example.kihunahn.seoulapp2018

import android.graphics.Color
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
    var lati1:ArrayList<String> = ArrayList()
    var loti1:ArrayList<String> = ArrayList()

    var dlati:ArrayList<Double> = ArrayList()
    var dloti:ArrayList<Double> = ArrayList()
    var dlati1:ArrayList<Double> = ArrayList()
    var dloti1:ArrayList<Double> = ArrayList()
    var cnum:Int = 0

    var odds = intArrayOf(0,16737280,Color.MAGENTA,Color.BLUE,Color.GREEN,13107455,Color.RED,16750080,65535)
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
        lati1 = arguments?.getStringArrayList("la1")!!
        loti1 = arguments?.getStringArrayList("lo1")!!
        cnum = arguments?.getInt("num")!!
        Log.d("QWE",cnum.toString())
        if(cnum==1){
            var len = lati.size-1
            for (i in 0..len) {
                dlati.add(java.lang.Double.parseDouble(lati[i]))
                dloti.add(java.lang.Double.parseDouble(loti[i]))
            }
            var len1 = lati1.size-1
            for (i in 0..len1) {
                dlati1.add(java.lang.Double.parseDouble(lati1[i]))
                dloti1.add(java.lang.Double.parseDouble(loti1[i]))
            }
        }
        else{
            var len = lati.size-1
            for (i in 0..len) {
                dlati.add(java.lang.Double.parseDouble(lati[i]))
                dloti.add(java.lang.Double.parseDouble(loti[i]))
            }
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
            val currentPoint = NGeoPoint(dlati[len/2], dloti[len/2])
            mapController!!.mapCenter = currentPoint

            if(cnum>1) {
                val poiData = NMapPOIdata(2, mapViewerResourceProvider)
                poiData.addPOIitem(dlati[0], dloti[0], "", NMapPOIflagType.FROM, 0)
                poiData.addPOIitem(dlati[len], dloti[len], "", NMapPOIflagType.TO, 0)
                poiData.endPOIdata()
                val poiDataOverlay = mapOverlayManager!!.createPOIdataOverlay(poiData, null)
                poiDataOverlay.showAllPOIdata(0)
                poiDataOverlay.onStateChangeListener = this
            }
            val pathData = NMapPathData(len)
            pathData.initPathData()

            for(i in 0..len) {
                if(i==0)
                    pathData.addPathPoint(dlati[i], dloti[i], NMapPathLineStyle.TYPE_SOLID)
                else
                    pathData.addPathPoint(dlati[i], dloti[i], 0)
            }
            pathData.endPathData()
            val pathDataOverlay = mapOverlayManager!!.createPathDataOverlay(pathData)
            pathDataOverlay.setLineColor(odds[cnum],1000)
        }

        if(dlati1.size>0){
            var len = dlati1.size-1
            val pathData = NMapPathData(len)
            pathData.initPathData()
            if(cnum==1) {
                val poiData = NMapPOIdata(2, mapViewerResourceProvider)
                poiData.addPOIitem(dlati1[0], dloti1[0], "", NMapPOIflagType.FROM, 0)
                poiData.addPOIitem(dlati1[len], dloti1[len], "", NMapPOIflagType.TO, 0)
                poiData.endPOIdata()
                val poiDataOverlay = mapOverlayManager!!.createPOIdataOverlay(poiData, null)
                poiDataOverlay.showAllPOIdata(0)
                poiDataOverlay.onStateChangeListener = this
            }
            for(i in 0..len) {
                if(i==0)
                    pathData.addPathPoint(dlati1[i], dloti1[i], NMapPathLineStyle.TYPE_SOLID)
                else
                    pathData.addPathPoint(dlati1[i], dloti1[i], 0)
            }
            pathData.endPathData()
            val pathDataOverlay = mapOverlayManager!!.createPathDataOverlay(pathData)
            pathDataOverlay.setLineColor(odds[cnum],1000)
        }
    }

    companion object {
        private val CLIENT_ID = "XrPrMsRtFpXGFaq_Az1I"// 애플리케이션 클라이언트 아이디 값
    }
}