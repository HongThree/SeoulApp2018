package com.example.kihunahn.seoulapp2018.Fragment

import android.view.ViewGroup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.example.kihunahn.seoulapp2018.R


class InfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_info, container, false)

        return view
    }

}