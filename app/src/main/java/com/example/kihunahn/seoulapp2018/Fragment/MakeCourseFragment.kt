package com.example.kihunahn.seoulapp2018.Fragment


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.CourseDTO
import com.example.kihunahn.seoulapp2018.PictureDTO
import com.example.kihunahn.seoulapp2018.PositionDTO
import com.example.kihunahn.seoulapp2018.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.coursename_dialog.view.*
import kotlinx.android.synthetic.main.fragment_makecourse.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MakeCourseFragment : Fragment(){

    var PositionList = PositionDTO(ArrayList(), ArrayList())
    var PictureList = PictureDTO(PositionList, ArrayList())

    var courseName = String()
    val cur_user = getUserId()
    var nameList = LinkedList<String>()
    var currentPath: String? = null
    val TAKE_PICTURE = 3
    var mapfragment:Fragment2?=null
    var dlati: ArrayList<Double>? = null
    var dloti: ArrayList<Double>? = null
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
        Toast.makeText(activity, fragmentManager!!.backStackEntryCount.toString(), Toast.LENGTH_LONG).show()

        var dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        var date = Date()
        courseName = dateFormat.format(date).toString()

        btn_exit.setOnClickListener {
            var size = mapfragment!!.dlati.size-1
            dlati= mapfragment!!.dlati
            dloti = mapfragment!!.dloti
            for (i in size downTo 0){
                PositionList.lat?.add(dlati!![i])
                PositionList.lon?.add(dloti!![i])
            }
            mapfragment!!.mLocationManager!!.removeUpdates(mapfragment!!.mLocationListener)
            showDialog(courseName)
            fragmentManager!!.popBackStack()

        }


        btn_takePicture.setOnClickListener {
            dispatchCameraIntent()
        }
        cur_location.setOnClickListener {
            Toast.makeText(activity,mapfragment!!.dloti.toString(),Toast.LENGTH_SHORT).show()
            mapfragment!!.move()
        }
    }

    fun getUserId() : String {
        var userEmail = FirebaseAuth.getInstance().currentUser!!.email
        var ret = userEmail!!.substring(0, userEmail!!.indexOf('@'))
        return ret
    }

    fun uploadImage(bitmap : Bitmap) {
        var baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        var data = baos.toByteArray()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
            var file = File(currentPath)
            val uri = Uri.fromFile(file)
            var bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, uri)

            //uploadImage(bitmap)
            PictureList.uri?.add(uri.toString())
            Toast.makeText(activity, PictureList.uri.toString(), Toast.LENGTH_LONG).show()
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
        return image
    }

    fun saveCourse(courseName : String) {
        var INFOR = CourseDTO(cur_user,courseName,"ASD",PositionDTO(dloti,dloti),null)
        FirebaseFirestore.getInstance().collection(cur_user.toString()).document(courseName).set(INFOR).addOnSuccessListener {

        }.addOnFailureListener { exception ->

        }

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
        FirebaseFirestore.getInstance().collection(cur_user).get().addOnSuccessListener { querySnapshot ->
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