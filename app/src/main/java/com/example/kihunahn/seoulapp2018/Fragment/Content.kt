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

class Content : Fragment() {
    var position: Int = 0
    var image: IntArray? = null
    var imageviewpager: ViewPager? = null
    var pageindicator: PageIndicatorView? = null
    var lati: DoubleArray = doubleArrayOf(127.127948, 127.127814, 127.127696, 127.127462, 127.127266, 127.127190, 127.128467, 127.129556)
    var loti: DoubleArray = doubleArrayOf(37.439937, 37.440627, 37.441257, 37.442470, 37.443503, 37.444041, 37.444412, 37.444740)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_content, container, false)

        position = arguments!!.getInt("adapter_pos")
        image = MainFragment.posts[position].images

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

        var fragment = Fragment3()
        var args = Bundle()
        args.putDoubleArray("lati", lati)
        args.putDoubleArray("loti", loti)
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

    inner class BannerPagerAdapter(var fm: FragmentManager, var imagesList: IntArray) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return ContentFragment.getInstance(imagesList[position])
        }

        override fun getCount(): Int {
            return imagesList.size
        }
    }
}