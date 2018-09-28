package com.example.kihunahn.seoulapp2018.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.R
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.fragment_content.*
import kotlinx.android.synthetic.main.fragment_content.view.*

class Content2 : Fragment() {

    var position: Int = 0
    var image: ArrayList<String>? = null
    var imageviewpager: ViewPager? = null
    var pageindicator: PageIndicatorView? = null
    var tlati:ArrayList<Double>? = null
    var tloti:ArrayList<Double>? = null
    var lati: DoubleArray? = null
    var loti: DoubleArray? = null
    var stamp:ArrayList<Boolean>?=null
    var stlst:BooleanArray?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_content, container, false)
//        position = arguments!!.getInt("adapter_pos")
//        image = MainFragment.posts[position].images

        image = arguments!!["PictureList"] as ArrayList<String>?
        tlati = arguments!!["latitude"] as ArrayList<Double>?
        tloti = arguments!!["longitude"] as ArrayList<Double>?
        stamp = arguments!!["stampList"] as ArrayList<Boolean>?
        lati = tlati!!.toDoubleArray()
        loti = tloti!!.toDoubleArray()
        stlst = stamp!!.toBooleanArray()

        if(image != null) {
            view.pager.visibility = View.VISIBLE
            view.feed.visibility = View.VISIBLE
            //pager?.visibility = View.VISIBLE
            //feed?.visibility = View.VISIBLE
            val bannerPagerAdapter = BannerPagerAdapter(childFragmentManager, image!!)
            imageviewpager = view.findViewById(R.id.pager) as ViewPager
            pageindicator = view.findViewById(R.id.feed) as PageIndicatorView
            imageviewpager!!.adapter = bannerPagerAdapter
            imageviewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                }

                override fun onPageSelected(position: Int) {
                    pageindicator!!.selection = position
                }

                override fun onPageScrollStateChanged(state: Int) {
                }
            })
        }
        else {
            pager?.visibility = View.GONE
            feed?.visibility = View.GONE
        }

        var fragment = Fragment3()
        var args = Bundle()
        args.putDoubleArray("lati", lati)
        args.putDoubleArray("loti", loti)
        args.putBooleanArray("stamp",stlst)
        fragment.arguments = args
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.Fragmenthere, fragment)
        fragmentTransaction.commit()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    inner class BannerPagerAdapter(var fm: FragmentManager, var imagesList: ArrayList<String>) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            //return ContentFragment.getInstance(imagesList[position])
            return ContentFragment2.getInstance(image!!.get(position))
        }
        override fun getCount(): Int {
            return imagesList.size
        }
    }
}