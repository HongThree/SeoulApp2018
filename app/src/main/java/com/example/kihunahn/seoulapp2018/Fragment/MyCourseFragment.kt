package com.example.kihunahn.seoulapp2018.Fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.Adapter.TravelListAdapter
import com.example.kihunahn.seoulapp2018.PlaceData
import com.example.kihunahn.seoulapp2018.PositionDTO
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.Server
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_archive.*
import kotlinx.android.synthetic.main.fragment_archive.view.*
import kotlinx.android.synthetic.main.fragment_mycourse.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class MyCourseFragment : Fragment() {
    var mGoogleApiClient: GoogleApiClient? = null
    private val REQUEST_CHECK_SETTINGS = 0x1
    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TravelListAdapter

    private val onItemClickListener = object : TravelListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            Toast.makeText(activity, "Clicked " + position, Toast.LENGTH_SHORT).show()

//            val nextFragment = DetailFragment()

            val nextFragment = Content()
            val bundle = Bundle()

            bundle.putSerializable("position", position)
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
            /*

            //val transitionIntent = DetailActivity.newIntent(this@MainActivity, position)

            val placeImage = view.findViewById<ImageView>(R.id.placeImage)
            val placeNameHolder = view.findViewById<LinearLayout>(R.id.placeNameHolder)


            //val navigationBar = findViewById<View>(android.R.id.navigationBarBackground)
            ///val statusBar = findViewById<View>(android.R.id.statusBarBackground)

            val imagePair = Pair.create(placeImage as View, "tImage")
            val holderPair = Pair.create(placeNameHolder as View, "tNameHolder")

            //val navPair = Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME)
            //val statusPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME)


            //val pairs = mutableListOf(imagePair, holderPair, statusPair, toolbarPair)
            val pairs = mutableListOf(imagePair, holderPair)
            */
            /*
            if (navigationBar != null && navPair != null) {
                pairs += navPair
            }
            */
            /*
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                    *pairs.toTypedArray())
            ActivityCompat.startActivity(this@MainActivity, transitionIntent, options.toBundle())
            */
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Getinformation().execute()
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
        //Getinformation().execute()
        //Toast.makeText(activity, PlaceData.placeNameArray.size.toString(), Toast.LENGTH_SHORT).show()
        /*
        var cur_user = getUserId()
        FirebaseFirestore.getInstance().collection(cur_user).get().addOnSuccessListener { querySnapshot ->
            // 이 유저에게 저장 된 여행의 개수 출력 됨!!
            //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
            //querySnapshot.documents.size

            querySnapshot.forEach {
                PlaceData.placeNameArray.add(it.id)

                Toast.makeText(activity, it.id, Toast.LENGTH_SHORT).show()
                //[lat,lon]
                //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()

                // 이게 실제 여행명 받아오기
                //Toast.makeText(activity, it.id, Toast.LENGTH_LONG).show()
            }
            Toast.makeText(activity, PlaceData.placeNameArray.size.toString(), Toast.LENGTH_SHORT).show()
        }
        */
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
                var cur_user = "kihunahn0721"
                try {
                    Log.e("ww","doInBackground2")

                    FirebaseFirestore.getInstance().collection(cur_user).get().addOnSuccessListener { querySnapshot ->
                        // 이 유저에게 저장 된 여행의 개수 출력 됨!!
                        //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
                        //querySnapshot.documents.size

                        querySnapshot.forEach {
                            Log.e("ww","ok?")
                            PlaceData.placeNameArray.add(it.id)
                            adapter.notifyDataSetChanged()
                            //[lat,lon]
                            //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()

                            // 이게 실제 여행명 받아오기
                            //Toast.makeText(activity, it.id, Toast.LENGTH_LONG).show()
                        }
                        Toast.makeText(activity, PlaceData.placeNameArray.size.toString(), Toast.LENGTH_SHORT).show()
                    }
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
}