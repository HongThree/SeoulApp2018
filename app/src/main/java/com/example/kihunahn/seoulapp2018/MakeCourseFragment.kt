package com.example.kihunahn.seoulapp2018

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_makecourse.*
import kotlinx.android.synthetic.main.fragment_mycourse.*
import java.util.*

class MakeCourseFragment : Fragment() {

    var PositionList = LinkedList<Position>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_makecourse, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        btn_exit.setOnClickListener {

            Toast.makeText(activity, "여행이 저장되었습니다.", Toast.LENGTH_LONG).show()
            val fragment = MyCourseFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        btn_add.setOnClickListener {
            Toast.makeText(activity, "입력 되었습니다", Toast.LENGTH_LONG).show()
            PositionList.add(Position(editText_lat.text.toString().toDouble(), editText_lon.text.toString().toDouble()))
            state.setText(PositionList.toString())
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

data class Position(var lat:Double?, var lon:Double?)