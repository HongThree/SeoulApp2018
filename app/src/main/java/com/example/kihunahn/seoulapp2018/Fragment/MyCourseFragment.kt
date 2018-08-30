package com.example.kihunahn.seoulapp2018.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_mycourse.*


class MyCourseFragment : Fragment() {
    var mGoogleApiClient: GoogleApiClient? = null
    private val REQUEST_CHECK_SETTINGS = 0x1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_mycourse, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        Toast.makeText(activity, fragmentManager!!.backStackEntryCount.toString(), Toast.LENGTH_SHORT).show()
        btn_start.setOnClickListener {
            /*
            mGoogleApiClient = GoogleApiClient.Builder(activity!!)
                    .addApi(LocationServices.API)
                    .build()
            mGoogleApiClient!!.connect()
            showSettingDialog()
            */
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
    /*
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
}