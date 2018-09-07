package com.example.kihunahn.seoulapp2018

import android.app.Activity
import android.app.ProgressDialog
import android.os.AsyncTask
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Server(val context: Activity?) {
    companion object {
        var course_lo1: ArrayList<Double> = ArrayList()
        var course_la1: ArrayList<Double> = ArrayList()

        var course_lo2: ArrayList<Double> = ArrayList()
        var course_la2: ArrayList<Double> = ArrayList()

        var course_lo3: ArrayList<Double> = ArrayList()
        var course_la3: ArrayList<Double> = ArrayList()

        var course_lo4: ArrayList<Double> = ArrayList()
        var course_la4: ArrayList<Double> = ArrayList()

        var course_lo5: ArrayList<Double> = ArrayList()
        var course_la5: ArrayList<Double> = ArrayList()

        var course_lo6: ArrayList<Double> = ArrayList()
        var course_la6: ArrayList<Double> = ArrayList()

        var course_lo7: ArrayList<Double> = ArrayList()
        var course_la7: ArrayList<Double> = ArrayList()

        var course_lo8: ArrayList<Double> = ArrayList()
        var course_la8: ArrayList<Double> = ArrayList()

        var cnt = 1
    }

    fun setting() {
        if (course_la1.size == 0) {
            for (i in 1..8) {
                var url = "https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course=" + i.toShort()
                Getinformation().execute(url)
            }
        }
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
            var urlConnection: HttpURLConnection? = null
            try {
                val url = URL(urls[0])
                urlConnection = url.openConnection() as HttpURLConnection

                var inString = streamToString(urlConnection.inputStream)

                try {
                    var json = JSONObject(inString)
                    var tmp = json.getJSONArray("body")
                    var len = tmp.length() - 1
                    for (i in 0..len) {
                        val order = tmp.getJSONObject(i)
                        var name = order.getString("COT_CONTS_NAME")
                        var location: String = order.getString("COT_COORD_DATA")
                        parsingLocation(location, i, cnt)

                    }
                    cnt++
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
            asyncDialog.dismiss()
        }
    }

    fun parsingLocation(input: String, index: Int, cnt: Int) {
        val temp = input.replace("[", "").replace("]", "").split(",")
        var len = temp.size - 1
        Log.e("Server",len.toString())
        if (cnt == 1) {
            if (index == 0) {
                for (i in 0..len step 2) {
                    course_la1.add(java.lang.Double.parseDouble(String.format("%.6f", java.lang.Double.parseDouble(temp[i]))))
                    course_lo1.add(java.lang.Double.parseDouble(String.format("%.6f", java.lang.Double.parseDouble(temp[i+1]))))
                }
            } else {
                for (i in 0..len step 2) {
                    course_la1.add(java.lang.Double.parseDouble(String.format("%.6f", java.lang.Double.parseDouble(temp[i]))))
                    course_lo1.add(java.lang.Double.parseDouble(String.format("%.6f", java.lang.Double.parseDouble(temp[i+1]))))
                }
            }
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