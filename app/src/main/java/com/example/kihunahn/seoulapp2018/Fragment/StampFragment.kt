package com.example.kihunahn.seoulapp2018.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.Server.Companion.stamplst
import java.util.*


class StampFragment : Fragment() {

    var stamp_arr: ArrayList<ImageView> = ArrayList()
    var on_image: Array<Int> = arrayOf(R.drawable.stamp01_on, R.drawable.stamp02_on, R.drawable.stamp03_on, R.drawable.stamp04_on, R.drawable.stamp05_on,
            R.drawable.stamp06_on, R.drawable.stamp07_on, R.drawable.stamp08_on, R.drawable.stamp09_on, R.drawable.stamp10_on, R.drawable.stamp11_on,
            R.drawable.stamp12_on, R.drawable.stamp13_on, R.drawable.stamp14_on, R.drawable.stamp15_on, R.drawable.stamp16_on, R.drawable.stamp17_on,
            R.drawable.stamp18_on, R.drawable.stamp19_on, R.drawable.stamp20_on, R.drawable.stamp21_on, R.drawable.stamp22_on, R.drawable.stamp23_on,
            R.drawable.stamp24_on, R.drawable.stamp25_on, R.drawable.stamp26_on, R.drawable.stamp27_on, R.drawable.stamp28_on)
    var off_image: Array<Int> = arrayOf(R.drawable.stamp01, R.drawable.stamp02, R.drawable.stamp03, R.drawable.stamp04, R.drawable.stamp05,
            R.drawable.stamp06, R.drawable.stamp07, R.drawable.stamp08, R.drawable.stamp09, R.drawable.stamp10, R.drawable.stamp11,
            R.drawable.stamp12, R.drawable.stamp13, R.drawable.stamp14, R.drawable.stamp15, R.drawable.stamp16, R.drawable.stamp17,
            R.drawable.stamp18, R.drawable.stamp19, R.drawable.stamp20, R.drawable.stamp21, R.drawable.stamp22, R.drawable.stamp23,
            R.drawable.stamp24, R.drawable.stamp25, R.drawable.stamp26, R.drawable.stamp27, R.drawable.stamp28)
    var image_arr: Array<Int> = arrayOf(R.id.img1, R.id.img2, R.id.img3, R.id.img4, R.id.img5, R.id.img6, R.id.img7, R.id.img8, R.id.img9, R.id.img10,
            R.id.img11, R.id.img12, R.id.img13, R.id.img14, R.id.img15, R.id.img16, R.id.img17, R.id.img18, R.id.img19, R.id.img20,
            R.id.img21, R.id.img22, R.id.img23, R.id.img24, R.id.img25, R.id.img26, R.id.img27, R.id.img28)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_stamp, container, false)
        for (i in 0..27) {
            stamp_arr.add(view.findViewById<View>(image_arr[i]) as ImageView)
        }

        for (i in 0..27) {
            if(stamplst[i]) {
                stamp_arr[i].setImageResource(on_image[i])
                stamp_arr[i].tag = "on"
            }
            else {
                stamp_arr[i].setImageResource(off_image[i])
                stamp_arr[i].tag = "off"
            }
        }
//1코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[0].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[0].tag == "off") {
                builder.setTitle("1코스")
                builder.setMessage("아직 수락, 불암산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("1코스")
                builder.setMessage("수락, 불암산 코스 스탬프를 획득하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[1].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[1].tag == "off") {
                builder.setTitle("1코스")
                builder.setMessage("아직 수락, 불암산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("1코스")
                builder.setMessage("수락, 불암산 코스 스탬프를 획득하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[2].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[2].tag == "off") {
                builder.setTitle("1코스")
                builder.setMessage("아직 수락, 불암산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("1코스")
                builder.setMessage("수락, 불암산 코스 스탬프를 획득하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
//2코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[3].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[3].tag == "off") {
                builder.setTitle("2코스")
                builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("2코스")
                builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[4].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[4].tag == "off") {
                builder.setTitle("2코스")
                builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("2코스")
                builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[5].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[3].tag == "off") {
                builder.setTitle("2코스")
                builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("2코스")
                builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

//3코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[6].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[6].tag == "off") {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[7].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[7].tag == "off") {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[8].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[8].tag == "off") {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[9].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[9].tag == "off") {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("3코스")
                builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

//4코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[10].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[10].tag == "off") {
                builder.setTitle("4코스")
                builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("4코스")
                builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[11].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[11].tag == "off") {
                builder.setTitle("4코스")
                builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("4코스")
                builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[12].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[12].tag == "off") {
                builder.setTitle("4코스")
                builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("4코스")
                builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

//5코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[13].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[13].tag == "off") {
                builder.setTitle("5코스")
                builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("5코스")
                builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[14].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[14].tag == "off") {
                builder.setTitle("5코스")
                builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("5코스")
                builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[15].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[15].tag == "off") {
                builder.setTitle("5코스")
                builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("5코스")
                builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

//6코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[16].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[16].tag == "off") {
                builder.setTitle("6코스")
                builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("6코스")
                builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[17].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[17].tag == "off") {
                builder.setTitle("6코스")
                builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("6코스")
                builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[18].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[18].tag == "off") {
                builder.setTitle("6코스")
                builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("6코스")
                builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

//7코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[19].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[19].tag == "off") {
                builder.setTitle("7코스")
                builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("7코스")
                builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[20].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[20].tag == "off") {
                builder.setTitle("7코스")
                builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("7코스")
                builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[21].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[21].tag == "off") {
                builder.setTitle("7코스")
                builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }

            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("7코스")
                builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
//8코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        stamp_arr[22].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[22].tag == "off") {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[23].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[23].tag == "off") {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[24].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[24].tag == "off") {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[25].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[25].tag == "off") {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        stamp_arr[26].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[26].tag == "off") {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }
        stamp_arr[27].setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때
            if (stamp_arr[27].tag == "off") {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
            //else -> 스탬프 아직 안찍었을 때
            else {
                builder.setTitle("8코스")
                builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
                builder.setPositiveButton("확인", null)
                builder.show()
            }
        }

        return view
    }

}