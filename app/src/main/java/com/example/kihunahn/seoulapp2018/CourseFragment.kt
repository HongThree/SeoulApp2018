package com.example.kihunahn.seoulapp2018

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nightonke.boommenu.BoomButtons.OnBMClickListener
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton
import com.nightonke.boommenu.BoomMenuButton
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class CourseFragment : Fragment() {
    var str: String? = null
    var textview: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_course, container, false)
        textview = view.findViewById(R.id.text) as TextView
        Getinformation().execute("https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course=1")
        textview?.text = str
        str = ""
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        val bmb = view.findViewById(R.id.bmb) as BoomMenuButton
        val drawable = R.drawable::class.java
        for (i in 0 until bmb.buttonPlaceEnum.buttonNumber()) {
            var builder: SimpleCircleButton.Builder = SimpleCircleButton.Builder()
            var field = drawable.getField("road" + i)
            builder.normalImageRes(field.getInt(null))
                    .rotateImage(true)
                    .listener(OnBMClickListener { index ->
                        //Log.d("QWE", index.toString())
                        var url = "https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course="+(index+1).toShort()
                        Getinformation().execute(url)
                    })
            bmb.addBuilder(builder)
            //bmb.addBuilder(SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_one))
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
                        //var location = order.getString("COT_COORD_DATA")
                        var location:JSONArray = order.getJSONArray("COT_COORD_DATA")
                        Log.d("QWE",location.toString())
                        str += name
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
            textview?.text = str
            str = ""
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