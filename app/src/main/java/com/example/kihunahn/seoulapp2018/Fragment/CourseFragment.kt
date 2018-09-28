package com.example.kihunahn.seoulapp2018.Fragment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.Rect
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import com.example.kihunahn.seoulapp2018.R
import com.nightonke.boommenu.BoomButtons.OnBMClickListener
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton
import com.nightonke.boommenu.BoomMenuButton
import com.nightonke.boommenu.Util
import kotlinx.android.synthetic.main.fragment_course.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL



class CourseFragment : Fragment(){
    var mainFragment: Fragment? = null
    var scroll: ScrollView? = null
    var transparent: ImageView? = null
    var arrayList: ArrayList<String> = ArrayList()
    var arrayList2: ArrayList<String> = ArrayList()
    var arrayList3: ArrayList<String> = ArrayList()
    var arrayList4: ArrayList<String> = ArrayList()
    var num:Int=0

    @SuppressLint("ClickableViewAccessibility")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_course, container, false)

        Getinformation().execute("https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course=1")

        num = 1
        scroll = view.findViewById(R.id.snackbar_contaner) as ScrollView

        scroll!!.post {
            scroll!!.scrollTo(0, 0)

            scroll!!.fullScroll(ScrollView.FOCUS_UP)
            scroll!!.smoothScrollTo(0,0)
        }

        transparent = view.findViewById(R.id.imagetrans) as ImageView

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
        arrayList.clear()
        arrayList2.clear()

        val bmb = view.findViewById(R.id.bmb) as BoomMenuButton
        val drawable = R.drawable::class.java

        for (i in 0 until bmb.buttonPlaceEnum.buttonNumber()) {
            var builder: SimpleCircleButton.Builder = SimpleCircleButton.Builder()
            var field = drawable.getField("road" + i)
            var stam = drawable.getField("sta"+i)
            builder.normalImageRes(field.getInt(null))
                    .imageRect(Rect(Util.dp2px(0F), Util.dp2px(0F), Util.dp2px(70F), Util.dp2px(70F)))
                    .imagePadding( Rect(0, 0, 10, 10))
                    .rotateImage(true).isRound(false).buttonCornerRadius(Util.dp2px(20F))
                    .buttonRadius(Util.dp2px(34F)).shadowEffect(false)
                    .listener(OnBMClickListener { index ->
                        var url = "https://mplatform.seoul.go.kr/api/dule/courseInfo.do?course=" + (index + 1).toShort()
                        Getinformation().execute(url)

                        stamp_imageView.setImageResource(stam.getInt(null))
                        var conum = i+1
                        course_name.text = "$conum 코스"


                        num = index+1
                        arrayList.clear()
                        arrayList2.clear()
                        arrayList3.clear()
                        arrayList4.clear()
                    })
            bmb.addBuilder(builder)
        }

        bmb.builders[0].normalColor(Color.parseColor("#EB5919"))
        bmb.builders[1].normalColor(Color.parseColor("#ED62A7"))
        bmb.builders[2].normalColor(Color.parseColor("#0D6FB8"))
        bmb.builders[3].normalColor(Color.parseColor("#07913A"))
        bmb.builders[4].normalColor(Color.parseColor("#920783"))
        bmb.builders[5].normalColor(Color.parseColor("#C30D23"))
        bmb.builders[6].normalColor(Color.parseColor("#A57225"))
        bmb.builders[7].normalColor(Color.parseColor("#14195F"))
        return view
    }


    inner class Getinformation : AsyncTask<String, String, String>() {

        var asyncDialog: ProgressDialog = ProgressDialog(activity)
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
                    var len = tmp.length()-1
                    for (i in 0..len) {
                        val order = tmp.getJSONObject(i)
                        var name = order.getString("COT_CONTS_NAME")
                        var location: String = order.getString("COT_COORD_DATA")
                        parsingLocation(location,i)

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
            val bundle = Bundle(5)
            bundle!!.putInt("num",num)
            bundle!!.putStringArrayList("la", arrayList)
            bundle!!.putStringArrayList("lo", arrayList2)
            bundle!!.putStringArrayList("la1", arrayList3)
            bundle!!.putStringArrayList("lo1", arrayList4)

            val fragment2 = Fragment1()
            fragment2?.arguments = bundle
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentHere, fragment2)
            fragmentTransaction.commit()
            asyncDialog.dismiss()
            scroll!!.post {
                scroll!!.scrollTo(0, 0)

                scroll!!.fullScroll(ScrollView.FOCUS_UP)
                scroll!!.smoothScrollTo(0,0)
            }
        }
    }

    fun parsingLocation(input: String, index:Int) {
        val temp = input.replace("[", "").replace("]", "").split(",")
        var len = temp.size - 1

        if(index==0) {
            for (i in 0..len step 2) {
                arrayList.add(temp[i])
                arrayList2.add(temp[i + 1])
            }
        }
        else{
            for (i in 0..len step 2) {
                arrayList3.add(temp[i])
                arrayList4.add(temp[i + 1])
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

    override fun onResume() {
        super.onResume()
        scroll!!.post {
            scroll!!.scrollTo(0, 0)

            scroll!!.fullScroll(ScrollView.FOCUS_UP)
            scroll!!.smoothScrollTo(0,0)
        }
    }
}