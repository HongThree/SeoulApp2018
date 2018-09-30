package com.example.kihunahn.seoulapp2018

import android.app.Activity
import android.app.ProgressDialog
import android.os.AsyncTask
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.util.*

class Server(val context: Activity?) {
    companion object {
        var cnt = 0
        var stamplst: BooleanArray = booleanArrayOf(false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false)
    }

    var courseList: ArrayList<CourseDTO>? = ArrayList<CourseDTO>()
    var courseRef = FirebaseFirestore.getInstance().collection("posts")
    fun setting() {
        if (cnt == 0) {
            Getinformation().execute()
            //cnt = 1
        }
    }

    fun getUserId(): String {
        var userEmail = FirebaseAuth.getInstance().currentUser!!.email
        var ret = userEmail!!.substring(0, userEmail!!.indexOf('@'))
        return ret
    }

    inner class Getinformation : AsyncTask<String, String, String>() {
        var asyncDialog: ProgressDialog = ProgressDialog(context!!)
        override fun onPreExecute() {
            // Before doInBackground
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            asyncDialog.setMessage("로딩중입니다..")

            // show dialog
            asyncDialog.show()
        }

        override fun doInBackground(vararg urls: String?): String {
            try {
                try {
                    PlaceData.placeNameArray = ArrayList<String>()
                    PlaceData.placeArray = ArrayList<PictureDTO>()

                    courseRef.addSnapshotListener(EventListener<QuerySnapshot> { snapshots, e ->
                        if (e != null)
                            return@EventListener

                        for (dc in snapshots!!.documentChanges) {
                            if (dc.document.data.getValue("userName").equals(getUserId())) {
                                when (dc.type) {
                                    DocumentChange.Type.ADDED -> {
                                        var courseDTO = dc.document.toObject(CourseDTO::class.java)
                                        courseDTO.postId = dc.document.id
                                        Log.d("SERVER",courseDTO.stampList.toString())
                                        var ssize = courseDTO.stampList!!.size
                                        for(i in 0 until ssize){
                                            if(courseDTO.stampList!![i])
                                                stamplst[i] = true
                                        }
                                    }
                                }
                            }
                        }
                    })
                } catch (ex: Exception) {
                }
            } catch (ex: Exception) {

            } finally {

            }
            return "complete"
        }

        override fun onProgressUpdate(vararg values: String?) {

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            asyncDialog.dismiss()
        }
    }

}