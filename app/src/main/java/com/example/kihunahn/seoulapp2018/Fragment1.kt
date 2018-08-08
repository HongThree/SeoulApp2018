package com.example.kihunahn.seoulapp2018

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhn.android.maps.NMapContext
import com.nhn.android.maps.NMapView

class Fragment1 : Fragment() {
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

    companion object {
        private val CLIENT_ID = "XrPrMsRtFpXGFaq_Az1I"// 애플리케이션 클라이언트 아이디 값
    }
}