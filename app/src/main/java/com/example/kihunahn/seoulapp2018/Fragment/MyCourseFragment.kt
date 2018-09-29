package com.example.kihunahn.seoulapp2018.Fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.app.AlertDialog
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.Adapter.TravelListAdapter
import com.example.kihunahn.seoulapp2018.CourseDTO
import com.example.kihunahn.seoulapp2018.PictureDTO
import com.example.kihunahn.seoulapp2018.PlaceData
import com.example.kihunahn.seoulapp2018.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_archive.*
import kotlinx.android.synthetic.main.fragment_archive.view.*


class MyCourseFragment : Fragment() {
    var mGoogleApiClient: GoogleApiClient? = null
    private val REQUEST_CHECK_SETTINGS = 0x1
    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TravelListAdapter

    var courseList : ArrayList<CourseDTO>? = ArrayList<CourseDTO>()

    var courseRef = FirebaseFirestore.getInstance().collection("posts")

    private val onItemClickListener = object : TravelListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            //Toast.makeText(activity, "Clicked " + position, Toast.LENGTH_SHORT).show()

            val nextFragment = Content2()
            val bundle = Bundle()

            bundle.putSerializable("PictureList", courseList!!.get(position).PictureList)
            bundle.putSerializable("latitude", courseList!!.get(position).lat)
            bundle.putSerializable("longitude", courseList!!.get(position).lng)
            bundle.putSerializable("stampList", courseList!!.get(position).stampList)
            nextFragment.arguments = bundle
            val placeImage = view.findViewById<ImageView>(R.id.placeImage)
            val placeNameHolder = view.findViewById<LinearLayout>(R.id.placeNameHolder)

            val imagePair = Pair.create(placeImage as View, "tImage")
            val holderPair = Pair.create(placeNameHolder as View, "tNameHolder")

            val pairs = mutableListOf(imagePair, holderPair)

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()

            fragmentTransaction.addSharedElement(placeImage, "shared_image")
            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            fragmentTransaction.replace(R.id.container, nextFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        override fun onItemLongClick(view: View, position: Int) {
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle("알림")
            builder.setMessage("삭제 하시겠습니까?")
            builder.setNegativeButton("취소",null)
            builder.setPositiveButton("확인") { arg0, arg1 ->
                courseRef.document(courseList!!.get(position).postId!!).delete()
            }
            builder.show()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Getinformation().execute()
        Toast.makeText(activity, PlaceData.placeNameArray.size.toString(), Toast.LENGTH_SHORT).show()
        val view = inflater!!.inflate(R.layout.fragment_archive, container, false)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        if (view.list_view == null) {
            Toast.makeText(activity, "널이야 시발", Toast.LENGTH_LONG).show()
        }

        view.list_view.layoutManager = staggeredLayoutManager

        adapter = TravelListAdapter(activity!!)
        adapter.setOnItemClickListener(onItemClickListener)

        view.list_view.adapter = adapter

        return view
    }

    override fun onStart() {
        super.onStart()
        btn_start.setOnClickListener {
            mGoogleApiClient = GoogleApiClient.Builder(activity!!)
                    .addApi(LocationServices.API)
                    .build()
            mGoogleApiClient!!.connect()
            showSettingDialog()
        }

//        btn_archive.setOnClickListener {
//            val fragment2 = ArchiveFragment()
//            val fragmentManager = fragmentManager
//            val fragmentTransaction = fragmentManager!!.beginTransaction()
//            fragmentTransaction.replace(R.id.container, fragment2)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Getinformation().execute()
    }
    fun getUserId() : String {
        var userEmail = FirebaseAuth.getInstance().currentUser!!.email
        var ret = userEmail!!.substring(0, userEmail!!.indexOf('@'))
        return ret
    }


    private fun showSettingDialog() {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //Setting priotity of Location request to high
        locationRequest.interval = 30 * 1000
        locationRequest.fastestInterval = 5 * 1000//5 sec Time interval for location update
        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true) //this is the key ingredient to show dialog always when GPS is off

        Toast.makeText(activity, "showSettingDialog", Toast.LENGTH_SHORT).show()

        val result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build())
        result.setResultCallback(ResultCallback<LocationSettingsResult> { result ->
            var status = result.status
            var state = result.locationSettingsStates
            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS -> {
                    val fragment2 = MakeCourseFragment()
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                    fragmentTransaction.replace(R.id.container, fragment2)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                    this.startIntentSenderForResult(status.resolution.intentSender, REQUEST_CHECK_SETTINGS, null, 0, 0, 0, null)
                }
                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                }
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Toast.makeText(activity, "onActivityResult", Toast.LENGTH_SHORT).show()
        when (requestCode) {
        // Check for the integer request code originally supplied to startResolutionForResult().
            REQUEST_CHECK_SETTINGS -> when (resultCode) {
                Activity.RESULT_OK -> {
                    val fragment2 = MakeCourseFragment()
                    val fragmentManager = fragmentManager
                    val fragmentTransaction = fragmentManager!!.beginTransaction()
                    fragmentTransaction.replace(R.id.container, fragment2)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                Activity.RESULT_CANCELED -> {
                    Log.e("Settings", "Result Cancel")
                }
            }
        }
    }


    /*
    class ReadRecyclerViewAdapter(initList: ArrayList<PositionDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var list: ArrayList<PositionDTO>? = initList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_recyclerview, parent, false)
            return CustomViewHolder(view)
        }

        class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
            var textview_course_name = view!!.findViewById<TextView>(R.id.textView_course_name)
            var textview_position_count = view!!.findViewById<TextView>(R.id.textView_position_count)
        }

        override fun getItemCount(): Int {
            return list!!.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var customViewHolder = holder as CustomViewHolder
            customViewHolder.textview_course_name.text = list!!.get(position).toString()
            customViewHolder.textview_position_count.text = list!!.get(position).lat!!.size.toString()
        }

    }
    */


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
                //var cur_user = getUserId()
                var cur_user = getUserId()
                try {
                    Log.e("ww","doInBackground2")
                    PlaceData.placeNameArray = ArrayList<String>()
                    PlaceData.placeArray = ArrayList<PictureDTO>()

                    // FirebaseFirestore.getInstance().collection(cur_user).get().addOnSuccessListener { querySnapshot ->

                    /*
                    docRef.get().addOnSuccessListener { querySnapshot ->
                        // 이 유저에게 저장 된 여행의 개수 출력 됨!!
                        //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
                        //querySnapshot.documents.size


                        querySnapshot.forEach {
                            var courseDTO = it.toObject(CourseDTO::class.java)
                            courseDTO.CourseName = it.id

//                            var p1 = it.data.getValue("userId").toString()
//                            var p2 = it.id
//                            var p3 = it.data.getValue("lat") as ArrayList<Double>?
//                            var p4 = it.data.getValue("lng") as ArrayList<Double>?
//                            var p5 = it.data.getValue("pictureList") as ArrayList<String>?
//                            var p6:ArrayList<Boolean>? = it.data.getValue("stampList") as ArrayList<Boolean>?
//                            var p7 = it.data.getValue("like") as Int

                            PlaceData.placeNameArray.add(courseDTO.CourseName!!)
                            courseList!!.add(courseDTO)

                            //n개의 사진 --> 0 .. n-1
                            PlaceData.placeArray.add(PictureDTO(courseDTO.CourseName, courseDTO.PictureList))
                            //val exifInterface = ExifInterface("//storage/emulated/0/Android/data/com.example.kihunahn.seoulapp2018/files/Pictures/img1.jpg")

                            adapter.notifyDataSetChanged()
                            /*
                            if (!PlaceData.placeNameArray.contains(p2)){
                                PlaceData.placeNameArray.add(p2)
                                courseList!!.add(CourseDTO(p1, p2, p3, p4, p5))
                                //n개의 사진 --> 0 .. n-1
                                PlaceData.placeArray.add(PictureDTO(p2, p5))
                                val exifInterface = ExifInterface("//storage/emulated/0/Android/data/com.example.kihunahn.seoulapp2018/files/Pictures/img1.jpg")

                                adapter.notifyDataSetChanged()
                            }

                            */

                            //[lat,lon]
                            //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()

                            // 이게 실제 여행명 받아오기
                            //Toast.makeText(activity, it.id, Toast.LENGTH_LONG).show()
                        }
                        //Toast.makeText(activity, "저장 된 여행 수: " + courseList!!.size.toString(), Toast.LENGTH_SHORT).show()
                    }*/

                    courseRef.addSnapshotListener(EventListener<QuerySnapshot> { snapshots, e ->
                        if (e != null)
                            return@EventListener
                        /*
                        snapshots!!.forEach {
                            var courseDTO = it.toObject(CourseDTO::class.java)
                            courseDTO.CourseName = it.id

                            var isExist = false

                            courseList!!.forEach {
                                if (it.CourseName.equals(courseDTO.CourseName))
                                    isExist = true
                            }

                            if (!isExist) {
                                PlaceData.placeNameArray.add(courseDTO.CourseName!!)
                                courseList!!.add(courseDTO)
                                PlaceData.placeArray.add(PictureDTO(courseDTO.CourseName, courseDTO.PictureList))
                                adapter.notifyDataSetChanged()
                            }
                        }*/

                        for(dc in snapshots!!.documentChanges) {
                            if(dc.document.data.getValue("userName").equals(getUserId())) {
                                when (dc.type) {
                                    DocumentChange.Type.ADDED -> {
                                        var courseDTO = dc.document.toObject(CourseDTO::class.java)
                                        courseDTO.postId = dc.document.id

                                        var isExist = false

                                        courseList!!.forEach {
                                            if (it.postId.equals(courseDTO.postId))
                                                isExist = true
                                        }

                                        if (!isExist) {
                                            PlaceData.placeNameArray.add(courseDTO.CourseName!!)
                                            courseList!!.add(courseDTO)
                                            PlaceData.placeArray.add(PictureDTO(courseDTO.CourseName, courseDTO.PictureList))
                                            adapter.notifyDataSetChanged()
                                        }
                                    }

                                    DocumentChange.Type.REMOVED -> {
                                        var postId = dc.document.id

                                        var toRemove = -1

                                        for (idx in 0..courseList!!.size - 1) {
                                            if (courseList!!.get(idx).postId.equals(postId)) {
                                                toRemove = idx
                                                break;
                                            }
                                        }

                                        if (toRemove != -1) {
                                            PlaceData.placeNameArray.remove(courseList!!.get(toRemove).CourseName)
                                            var picIdx = -1
                                            for(idx in 0..PlaceData.placeArray.size-1) {
                                                if(PlaceData.placeArray.get(idx).name.equals(courseList!!.get(toRemove).CourseName)) {
                                                    picIdx = idx
                                                    break
                                                }
                                            }
                                            if(picIdx != -1)
                                                PlaceData.placeArray.remove(PlaceData.placeArray.get(picIdx))

                                            courseList!!.remove(courseList!!.get(toRemove))
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            }
                        }
                    })
                } catch (ex: Exception) {
                }
                //publishProgress(inString)
            } catch (ex: Exception) {

            } finally {

            }
            return "complete"
        }

        override fun onProgressUpdate(vararg values: String?) {
            Log.e("update","onProgressUpdate")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if(result.equals("complete"))
                asyncDialog.dismiss()
        }
    }

    fun getDrawble(fileName : String ) : Int {
        return context!!.resources.getIdentifier(fileName, "drawable", context!!.packageName)
    }

}