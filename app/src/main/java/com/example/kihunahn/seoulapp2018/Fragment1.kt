package com.example.kihunahn.seoulapp2018

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.NMap.NMapPOIflagType
import com.example.kihunahn.seoulapp2018.NMap.NMapViewerResourceProvider
import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapController
import com.nhn.android.maps.NMapView
import com.nhn.android.maps.maplib.NGeoPoint
import com.nhn.android.maps.nmapmodel.NMapError
import com.nhn.android.maps.overlay.NMapPOIdata
import com.nhn.android.maps.overlay.NMapPOIitem
import com.nhn.android.mapviewer.overlay.NMapOverlayManager
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay


class Fragment1 : Fragment(),NMapView.OnMapStateChangeListener, NMapPOIdataOverlay.OnStateChangeListener {
    var mapView:NMapView?=null
    var mapController:NMapController?=null
    var mapViewerResourceProvider:NMapViewerResourceProvider?=null
    var mapOverlayManager:NMapOverlayManager?=null

    override fun onMapCenterChangeFine(p0: NMapView?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnimationStateChange(p0: NMapView?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapInitHandler(NMapView: NMapView?, nMapError: NMapError?) {
        if (nMapError == null) {
            moveMapCenter()
        } else {
            Log.e("map init error", nMapError.message)
        }
    }

    override fun onZoomLevelChange(p0: NMapView?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapCenterChange(p0: NMapView?, p1: NGeoPoint?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFocusChanged(p0: NMapPOIdataOverlay?, p1: NMapPOIitem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCalloutClick(p0: NMapPOIdataOverlay?, p1: NMapPOIitem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mMapContext: NMapContext? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMapContext = NMapContext(super.getActivity()!!)
        mMapContext!!.onCreate()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mapView = view!!.findViewById<View>(R.id.mapView) as NMapView
        mapView.setClientId(CLIENT_ID)// 클라이언트 아이디 설정
        mapView.isClickable = true
        mapView.isEnabled = true
        mapView.isFocusable = true
        mapView.isFocusableInTouchMode = true
        mapView.requestFocus()
        //mapView.setBuiltInZoomControls(true, null)
        //mapView.setOnMapStateChangeListener(this)
        /*mapController = mapView.mapController
        mapViewerResourceProvider = NMapViewerResourceProvider(activity)
        mapOverlayManager = NMapOverlayManager(activity!!, mapView, mapViewerResourceProvider)
        moveMapCenter()*/
        mMapContext?.setupMapView(mapView)
    }

    override fun onStart() {
        super.onStart()
        mMapContext?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mMapContext?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapContext?.onPause()
    }

    override fun onStop() {
        mMapContext?.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        mMapContext?.onDestroy()
        super.onDestroy()
    }

    fun moveMapCenter(){
        val currentPoint = NGeoPoint(126.979, 37.567)
        mapController!!.mapCenter = currentPoint
        val poiData = NMapPOIdata(2, mapViewerResourceProvider)
        poiData.addPOIitem(126.979, 37.567, "현재 위치", NMapPOIflagType.FROM, 0)
        poiData.addPOIitem(126.984, 37.565, "도착 위치", NMapPOIflagType.TO, 0)
        poiData.endPOIdata()
        val poiDataOverlay = mapOverlayManager!!.createPOIdataOverlay(poiData, null)
        poiDataOverlay.showAllPOIdata(0)
        poiDataOverlay.onStateChangeListener = this
    }

    companion object {
        private val CLIENT_ID = "XrPrMsRtFpXGFaq_Az1I"// 애플리케이션 클라이언트 아이디 값
    }
}