package com.example.kihunahn.seoulapp2018.Fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_stamp.*


class StampFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_stamp, container, false)

        val cor1 = view.findViewById<View>(R.id.img1) as ImageView
        val cor2 = view.findViewById<View>(R.id.img2) as ImageView
        val cor3 = view.findViewById<View>(R.id.img3) as ImageView
        val cor4 = view.findViewById<View>(R.id.img4) as ImageView
        val cor5 = view.findViewById<View>(R.id.img5) as ImageView
        val cor6 = view.findViewById<View>(R.id.img6) as ImageView
        val cor7 = view.findViewById<View>(R.id.img7) as ImageView
        val cor8 = view.findViewById<View>(R.id.img8) as ImageView
        val cor9 = view.findViewById<View>(R.id.img9) as ImageView
        val cor10 = view.findViewById<View>(R.id.img10) as ImageView
        val cor11 = view.findViewById<View>(R.id.img11) as ImageView
        val cor12 = view.findViewById<View>(R.id.img12) as ImageView
        val cor13 = view.findViewById<View>(R.id.img13) as ImageView
        val cor14 = view.findViewById<View>(R.id.img14) as ImageView
        val cor15 = view.findViewById<View>(R.id.img15) as ImageView
        val cor16 = view.findViewById<View>(R.id.img16) as ImageView
        val cor17 = view.findViewById<View>(R.id.img17) as ImageView
        val cor18 = view.findViewById<View>(R.id.img18) as ImageView
        val cor19 = view.findViewById<View>(R.id.img19) as ImageView
        val cor20 = view.findViewById<View>(R.id.img20) as ImageView
        val cor21 = view.findViewById<View>(R.id.img21) as ImageView
        val cor22 = view.findViewById<View>(R.id.img22) as ImageView
        val cor23 = view.findViewById<View>(R.id.img23) as ImageView
        val cor24 = view.findViewById<View>(R.id.img24) as ImageView
        val cor25 = view.findViewById<View>(R.id.img25) as ImageView
        val cor26 = view.findViewById<View>(R.id.img26) as ImageView
        val cor27 = view.findViewById<View>(R.id.img27) as ImageView
        val cor28 = view.findViewById<View>(R.id.img28) as ImageView
//1코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor1.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("1코스")
            builder.setMessage("아직 수락, 불암산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("수락, 불암산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor2.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("1코스")
            builder.setMessage("아직 수락, 불암산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("수락, 불암산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor3.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("1코스")
            builder.setMessage("아직 수락, 불암산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("수락, 불암산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
//2코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor4.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("2코스")
            builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("용마, 아차산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor5.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("2코스")
            builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("용마, 아차산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor6.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("2코스")
            builder.setMessage("아직 용마, 아차산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("용마, 아차산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

//3코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor7.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("3코스")
            builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("고덕, 일자산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor8.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("3코스")
            builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("고덕, 일자산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor9.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("3코스")
            builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("고덕, 일자산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor10.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("3코스")
            builder.setMessage("아직 고덕, 일자산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("고덕, 일자산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
//4코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor11.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("4코스")
            builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("대모, 우면산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor12.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("4코스")
            builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("대모, 우면산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor13.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("4코스")
            builder.setMessage("아직 대모, 우면산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("대모, 우면산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

//5코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor14.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("5코스")
            builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("관악산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor15.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("5코스")
            builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("관악산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor16.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("5코스")
            builder.setMessage("아직 관악산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("관악산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

//6코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor17.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("6코스")
            builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("안양천 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor18.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("6코스")
            builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("안양천 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor19.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("6코스")
            builder.setMessage("아직 안양천 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("안양천 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

//7코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor20.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("7코스")
            builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("봉산, 앵봉산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor21.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("7코스")
            builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("봉산, 앵봉산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor22.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("7코스")
            builder.setMessage("아직 봉산, 앵봉산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("봉산, 앵봉산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
//8코스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        cor23.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("8코스")
            builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("북한산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor24.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("8코스")
            builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("북한산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor25.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("8코스")
            builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("북한산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor26.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("8코스")
            builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("북한산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        cor27.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("8코스")
            builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("북한산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }
        cor28.setOnClickListener {
            val builder = AlertDialog.Builder(activity!!)

            //if -> 스탬프 아직 안찍었을 때

            builder.setTitle("8코스")
            builder.setMessage("아직 북한산 코스 스탬프를 획득하지 못하셨습니다.")
            builder.setPositiveButton("확인",null)
            builder.show()

            //else -> 스탬프 아직 안찍었을 때
//            builder.setTitle("1코스")
//            builder.setMessage("북한산 코스 스탬프를 획득하셨습니다.")
//            builder.setPositiveButton("확인",null)
//            builder.show()
        }

        return view
    }

}