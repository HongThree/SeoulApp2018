package com.example.kihunahn.seoulapp2018

import android.view.ViewGroup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View

import kotlinx.android.synthetic.main.fragment_course.*
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum
import com.nightonke.boommenu.Piece.PiecePlaceEnum
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.nightonke.boommenu.BoomButtons.HamButton
import com.nightonke.boommenu.ButtonEnum
import com.nightonke.boommenu.BoomMenuButton


class CourseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_course, container, false)



        return view
    }
}