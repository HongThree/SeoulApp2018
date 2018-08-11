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
    val cur_user = FirebaseAuth.getInstance().currentUser?.uid
    var nameList = LinkedList<String>()

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
        FirebaseFirestore.getInstance().collection(cur_user.toString()).document(courseName).set(PositionList).addOnSuccessListener {
            Toast.makeText(activity, "여행이 저장되었습니다.", Toast.LENGTH_LONG).show()
        }.addOnFailureListener { exception ->
            Toast.makeText(activity, exception.toString(), Toast.LENGTH_LONG).show()
        }
    }
    fun showDialog() {
        updateNameList()

        val mDialogView = LayoutInflater.from(activity).inflate(R.layout.coursename_dialog, null)
        val mBuilder = AlertDialog.Builder(activity)
                .setView(mDialogView)
                .setTitle("여행 이름 정하기")

        val mAlerDialog = mBuilder.show()

        mDialogView.btn_set_course_name.setOnClickListener {


            var tempName = mDialogView.editText_course_name.text.toString()
            if(!isValid(tempName)){
                Toast.makeText(activity, "이미 존재하는 이름입니다. 다시 입력 해주세요.", Toast.LENGTH_LONG).show()
            }
            else {
                if (!tempName.equals(""))
                    courseName = mDialogView.editText_course_name.text.toString()

                saveCourse()
                mAlerDialog.dismiss()
            }
        }
        mDialogView.btn_defualt_course_name.setOnClickListener {
            mAlerDialog.dismiss()
            Toast.makeText(activity, courseName, Toast.LENGTH_LONG).show()
            saveCourse()
        }

    }
    fun isValid(name : String) : Boolean {
        for( n in nameList){
            if(name.equals(n)) return false
        }
        return true
    }

    fun updateNameList() {
        FirebaseFirestore.getInstance().collection(cur_user.toString()).get().addOnSuccessListener { querySnapshot ->
            // 이 유저에게 저장 된 여행의 개수 출력 됨!!
            //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
            //querySnapshot.documents.size

            querySnapshot.forEach {
                //[lat,lon]
                //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()

                // 이게 실제 여행명 받아오기
                nameList.add(it.id)
            }
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

