package com.example.kihunahn.seoulapp2018

import android.view.ViewGroup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_mycourse.*


class MyCourseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_mycourse, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        btn_start.setOnClickListener {
            val fragment2 = MakeCourseFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment2)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        btn_archive.setOnClickListener {
            val fragment2 = ArchiveFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment2)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

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

    override fun onDetach() {
        super.onDetach()
    }
}