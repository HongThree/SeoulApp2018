package com.example.kihunahn.seoulapp2018.Fragment


//import com.google.firebase.storage.FirebaseStorage
//import io.realm.Realm
//import io.realm.RealmConfiguration
//import io.realm.RealmObject
//import io.realm.RealmResults
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.CourseDTO
import com.example.kihunahn.seoulapp2018.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.coursename_dialog.view.*
import kotlinx.android.synthetic.main.fragment_makecourse.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MakeCourseFragment : Fragment(){
    var latitude = java.util.ArrayList<Double>()
    var longitude = java.util.ArrayList<Double>()

    var PictureList = ArrayList<String>()
    var UpdatedPictureList = ArrayList<String>()

    var postId = String()
    var StampList = ArrayList<Boolean>()
    var courseName = String()
    var nameList = LinkedList<String>()
    var currentPath: String? = null
    val TAKE_PICTURE = 3
    var mapfragment:Fragment2?=null
    val cur_user = getUserId()
    var like= ArrayList<String>()
    var end = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_makecourse, container, false)

        mapfragment = Fragment2()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentHere, mapfragment!!)
        fragmentTransaction.commit()
        return view
    }



    override fun onStart() {
        super.onStart()


        var dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        var date = Date()
        courseName = dateFormat.format(date).toString()

        btn_exit.bringToFront()
        btn_exit.setMagicButtonClickListener {
            var size = mapfragment!!.dlati.size-1
            var dlati= mapfragment!!.dlati
            var dloti = mapfragment!!.dloti

            for (i in size downTo 0){
                //PositionList.lat?.add(dlati[i])
                //PositionList.lon?.add(dloti[i])
            }
            mapfragment!!.mLocationManager!!.removeUpdates(mapfragment!!.mLocationListener)
            //mapfragment!!.mMapLocationManager!!.removeOnLocationChangeListener(mapfragment!!.onMyLocationChangeListener)
            showDialog(courseName)

            /*
            val fragment = MyCourseFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
            //fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            */
        }


        btn_takePicture.bringToFront()
        btn_takePicture.setOnClickListener {
            dispatchCameraIntent()
        }
        cur_location.bringToFront()
        cur_location.setOnClickListener {
            mapfragment!!.move()
        }
    }

    fun getUserId() : String {
        var userEmail = FirebaseAuth.getInstance().currentUser!!.email
        var ret = userEmail!!.substring(0, userEmail!!.indexOf('@'))
        return ret
    }

//    fun uploadImage(bitmap : Bitmap) {
//        var baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
//        var data = baos.toByteArray()
    // FirebaseFirestore.getInstance().collection(cur_user.toString()).document(courseName).set(PositionList)
//        FirebaseStorage.getInstance().reference.child(cur_user.toString()).child(courseName+"_pic").putBytes(data)
//                .addOnCompleteListener { task->
//                    if(task.isSuccessful) {
//                        Toast.makeText(activity, "업로드에 성공하였습니다.", Toast.LENGTH_LONG).show()
//                    }
//                }
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
//            var file = File(currentPath)
            //uploadImage(bitmap)
//            PictureList.add(file.toString())
//            Toast.makeText(activity, "Size: " + PictureList.size.toString(), Toast.LENGTH_LONG).show()
//            Toast.makeText(activity, currentPath, Toast.LENGTH_LONG).show()
//            imageView.setImageURI(uri)
        }
    }


    fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(activity!!.packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImage()
            } catch (e : IOException){
                e.printStackTrace()
            }

            if(photoFile != null) {
                var photoUri = FileProvider.getUriForFile(activity!!, "com.example.kihunahn.seoulapp2018.Fragment", photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(intent, TAKE_PICTURE)
            }
        }
    }

    fun createImage(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName = "JPEG_"+timeStamp+"_"
        var storageDir = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var image = File.createTempFile(imageName, ".jpg", storageDir)
        currentPath = image.absolutePath
        PictureList.add(currentPath!!)

        //saveCourse("")
        return image
    }

    fun saveCourse(courseName : String) {
        /*
        data class CourseDTO(var userId : String? = null,
                             var CourseName : String? = null,
                             var lat : ArrayList<Double>? = null,
                             var lng : ArrayList<Double>? = null,
                             var PictureList : ArrayList<String>? = null,
                             var stampList : BooleanArray? = BooleanArray(28) )
        */
//        var myCourse = CourseDTO(cur_user, courseName, dlati, dloti, PictureList, StampList, like)

//        FirebaseFirestore.getInstance().collection("posts").document().set(myCourse).addOnSuccessListener {

//        }.addOnFailureListener { exception ->

//        }
//        end = PictureList.size
        Getinformation().execute()



//        FirebaseFirestore.getInstance().collection(cur_user).document(courseName).set(myCourse).addOnSuccessListener {
//
//        }.addOnFailureListener { exception ->
//
//        }
    }


    fun showDialog(defaultName : String) {
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

                saveCourse(courseName)
                mAlerDialog.dismiss()
            }
        }
        mDialogView.btn_defualt_course_name.setOnClickListener {
            mAlerDialog.dismiss()
            //Toast.makeText(activity, defaultName + "가 저장 되었습니다.", Toast.LENGTH_LONG).show()
            saveCourse(defaultName)
        }


    }

    fun isValid(name : String) : Boolean {
        for( n in nameList){
            if(name.equals(n)) return false
        }
        return true
    }

    fun updateNameList() {
        FirebaseFirestore.getInstance().collection("posts").get().addOnSuccessListener { querySnapshot ->
            // 이 유저에게 저장 된 여행의 개수 출력 됨!!
            //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
            //querySnapshot.documents.size

            querySnapshot.forEach {
                //[lat,lon]
                //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()

                // 이게 실제 여행명 받아오기
                if(it.data.getValue("userName").equals(cur_user))
                    nameList.add(it.data.getValue("courseName") as String)
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
            Log.e("ww","doInBackground1")
            try {
                for(i in 0..PictureList.size-1)
                    Log.d("loc", PictureList.get(i))

                //var cur_user = getUserId()
                for(i in 0 until PictureList.size) {
                    //for(i in 0 ..1) {
                    Log.d("ssss", "for: " + i.toString() )


                    var bitmap : Bitmap? = MediaStore.Images.Media.getBitmap(activity!!.getContentResolver() , Uri.parse(File(PictureList.get(i)).toURI().toString()))
                    if(bitmap == null)
                        Log.d("ssss", "null " + i.toString())
                    if(bitmap != null) {
                        var baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                        var data = baos.toByteArray()
                        Log.d("ssss", i.toString() + "Wwwwwwwwwwwwwwwwwwwwwww")
                        Log.d("ssss", PictureList.get(i))

                        FirebaseStorage.getInstance().reference.child(cur_user + "//" + courseName).child(i.toString()).putBytes(data)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        UpdatedPictureList.add(task.result.downloadUrl.toString())
                                        Log.e("check", task.result.downloadUrl.toString())
                                    } else {
                                        Log.e("check", "fail")
                                    }
                                }
                    }
                }
            } catch (ex: Exception) {

            } finally {
                Log.d("ssss", "finally")

            }
            while(PictureList.size != UpdatedPictureList.size) {
                Log.d("ssss",PictureList.size.toString() + " " + UpdatedPictureList.size.toString())

                Thread.sleep(1000)
            }
            Log.d("ssss", "ffff")

            return "complete"
        }

        override fun onProgressUpdate(vararg values: String?) {
            Log.e("update","onProgressUpdate")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if(result.equals("complete"))
                asyncDialog.dismiss()
            fragmentManager?.popBackStack()
            var myCourse = CourseDTO(cur_user, courseName, latitude, longitude, UpdatedPictureList, StampList, like, postId)
            FirebaseFirestore.getInstance().collection("posts").document().set(myCourse).addOnSuccessListener {

            }.addOnFailureListener { exception ->


            }
        }

    }
}

//open class Course : RealmObject() {
//    var course_name : String? = null
//    var lats : String? = null
//    var lons : String? = null
//}