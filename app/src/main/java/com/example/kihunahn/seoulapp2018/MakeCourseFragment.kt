package com.example.kihunahn.seoulapp2018

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.coursename_dialog.view.*
import kotlinx.android.synthetic.main.fragment_makecourse.*
import kotlinx.android.synthetic.main.fragment_mycourse.*
import java.text.SimpleDateFormat
import java.util.*

class MakeCourseFragment : Fragment() {

    var PositionList = PositionDTO(LinkedList(), LinkedList())
    var courseName = String()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_makecourse, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()
        var dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        var date = Date()
        courseName = dateFormat.format(date).toString()

        btn_exit.setOnClickListener {
            showDialog()
            val fragment = MyCourseFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        btn_add.setOnClickListener {
            PositionList.lat?.add(editText_lat.text.toString().toDouble())
            PositionList.lon?.add(editText_lon.text.toString().toDouble())
            state.setText(PositionList.toString())
        }

    }
    fun saveCourse() {
        var cur_user = FirebaseAuth.getInstance().currentUser?.uid
        FirebaseFirestore.getInstance().collection(cur_user.toString()).document(courseName).set(PositionList).addOnSuccessListener {
            Toast.makeText(activity, "여행이 저장되었습니다.", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { exception ->
            Toast.makeText(activity, exception.toString(), Toast.LENGTH_LONG).show()
        }
    }
    fun showDialog() {
        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.coursename_dialog, null)
        val mBuilder = AlertDialog.Builder(activity)
                .setView(mDialogView)
                .setTitle("여행 이름 정하기")

        val mAlerDialog = mBuilder.show()

        mDialogView.btn_set_course_name.setOnClickListener {
            mAlerDialog.dismiss()
            if(!mDialogView.editText_course_name.text.toString().equals(""))
                courseName = mDialogView.editText_course_name.text.toString()

            saveCourse()
        }
        mDialogView.btn_defualt_course_name.setOnClickListener {
            mAlerDialog.dismiss()
            Toast.makeText(activity, courseName, Toast.LENGTH_LONG).show()
            saveCourse()
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

