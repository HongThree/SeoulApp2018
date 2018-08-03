package com.example.kihunahn.seoulapp2018

import android.content.Context
import android.graphics.Color
import android.widget.RelativeLayout
import android.view.ViewGroup
import android.graphics.Typeface
import android.widget.TextView
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.Toast
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View

import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_main, container, false)

//        val pager: ViewPager? = pager
//        pager?.adapter = PagerAdapter()

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        button.setOnClickListener{
//            Toast.makeText(this.context, "Clicked a button!", Toast.LENGTH_SHORT).show()
//
//        }
//    }

//    private inner class PagerAdapter : android.support.v4.view.PagerAdapter() {
//
//        override fun getCount(): Int {
//            return 6
//        }
//
//        override fun isViewFromObject(view: View, `object`: Any): Boolean {
//            return view === `object`
//        }
//
//        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//
//            // Create some layout params
//            val layoutParams = RelativeLayout.LayoutParams(
//                    RelativeLayout.LayoutParams.WRAP_CONTENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT)
//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
//
//            // Create some text
//            val textView = getTextView(container.context)
//            textView.text = position.toString()
//            textView.layoutParams = layoutParams
//
//            val layout = RelativeLayout(container.context)
//            layout.setBackgroundColor(ContextCompat.getColor(container.context, R.color.colorPrimary))
//            layout.layoutParams = layoutParams
//
//            layout.addView(textView)
//            container.addView(layout)
//            return layout
//        }
//
//        private fun getTextView(context: Context): TextView {
//            val textView = TextView(context)
//            textView.setTextColor(Color.WHITE)
//            textView.textSize = 30f
//            textView.typeface = Typeface.DEFAULT_BOLD
//            return textView
//        }
//
//        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            container.removeView(`object` as RelativeLayout)
//        }
//    }
}