package com.example.kihunahn.seoulapp2018

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import com.nightonke.boommenu.BoomButtons.OnBMClickListener
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton
import com.nightonke.boommenu.BoomMenuButton
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CourseFragment : Fragment() {
    var scroll : ScrollView? = null
    var transparent : ImageView? = null
    var arrayList:ArrayList<String>?=null
    var arrayList2:ArrayList<String>?=null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_course, container, false)
        scroll = view.findViewById(R.id.snackbar_contaner) as ScrollView
        transparent = view.findViewById(R.id.imagetrans) as ImageView
        Getinformation().execute("https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course=1")

        transparent!!.setOnTouchListener(View.OnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    scroll!!.requestDisallowInterceptTouchEvent(true)
                    // Disable touch on transparent view
                    false
                }

                MotionEvent.ACTION_UP -> {
                    scroll!!.requestDisallowInterceptTouchEvent(false)
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    scroll!!.requestDisallowInterceptTouchEvent(true)
                    false
                }
                else -> true
            }
        })
        arrayList?.clear()
        arrayList2?.clear()

        val bmb = view.findViewById(R.id.bmb) as BoomMenuButton
        val drawable = R.drawable::class.java
        for (i in 0 until bmb.buttonPlaceEnum.buttonNumber()) {
            var builder: SimpleCircleButton.Builder = SimpleCircleButton.Builder()
            var field = drawable.getField("road" + i)
            builder.normalImageRes(field.getInt(null))
                    .rotateImage(true)
                    .listener(OnBMClickListener { index ->
                        var url = "https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course=" + (index + 1).toShort()
                        Getinformation().execute(url)
                        /*val fragment2 = Fragment1()
                        val fragmentManager = fragmentManager
                        val fragmentTransaction = fragmentManager!!.beginTransaction()
                        fragmentTransaction.replace(R.id.fragmentHere, fragment2)
                        fragmentTransaction.commit()*/
                    })
            bmb.addBuilder(builder)
        }
        return view
    }

    inner class Getinformation : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            // Before doInBackground
        }

        override fun doInBackground(vararg urls: String?): String {
            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection

                var inString = streamToString(urlConnection.inputStream)

                try {
                    var json = JSONObject(inString)
                    var tmp = json.getJSONArray("body")
                    for (i in 0..tmp.length()) {
                        val order = tmp.getJSONObject(i)
                        var name = order.getString("COT_CONTS_NAME")
                        var location: String= order.getString("COT_COORD_DATA")
                        val temp = location.replace("[","").replace("]","").split(",")
                    }
                } catch (ex: Exception) {

                }
                //publishProgress(inString)
            } catch (ex: Exception) {

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect()
                }
            }
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val bundle = Bundle(2)
            Log.d("QWEE",arrayList?.size.toString()+" "+arrayList2?.size.toString())
            bundle!!.putStringArrayList("la",arrayList)
            bundle!!.putStringArrayList("lo",arrayList2)
            val fragment2 = Fragment1()
            fragment2?.arguments = bundle
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentHere, fragment2)
            fragmentTransaction.commit()
        }
    }

    fun streamToString(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
    }
}