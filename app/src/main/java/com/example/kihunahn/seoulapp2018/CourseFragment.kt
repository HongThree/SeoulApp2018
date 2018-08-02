package com.example.kihunahn.seoulapp2018

import com.imangazaliev.circlemenu.CircleMenuButton

import com.imangazaliev.circlemenu.CircleMenu
import android.support.design.widget.Snackbar
import android.view.ViewGroup
import android.support.v7.app.AppCompatDelegate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.fragment_course.*


class CourseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_course, container, false)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        return view

        val snackbarContainer = snackbar_contaner
        val circleMenu = circle_menu
        circleMenu.setOnItemClickListener(CircleMenu.OnItemClickListener { menuButton -> Snackbar.make(snackbarContainer, menuButton.hintText, Snackbar.LENGTH_LONG).show() })

        circleMenu.setEventListener(object : CircleMenu.EventListener {
            override fun onMenuOpenAnimationStart() {
                Log.d("CircleMenuStatus", "onMenuOpenAnimationStart")
            }
            override fun onMenuOpenAnimationEnd() {
                Log.d("CircleMenuStatus", "onMenuOpenAnimationEnd")
            }
            override fun onMenuCloseAnimationStart() {
                Log.d("CircleMenuStatus", "onMenuCloseAnimationStart")
            }
            override fun onMenuCloseAnimationEnd() {
                Log.d("CircleMenuStatus", "onMenuCloseAnimationEnd")
            }
            override fun onButtonClickAnimationStart(menuButton: CircleMenuButton) {
                Log.d("CircleMenuStatus", "onButtonClickAnimationStart")
            }
            override fun onButtonClickAnimationEnd(menuButton: CircleMenuButton) {
                Log.d("CircleMenuStatus", "onButtonClickAnimationEnd")
            }

        })
    }
}