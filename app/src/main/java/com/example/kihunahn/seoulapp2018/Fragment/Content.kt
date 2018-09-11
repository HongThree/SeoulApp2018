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

class Content : Fragment(){
    var position :Int = 0
    var image:IntArray?=null
    var imageviewpager : ViewPager? = null
    var pageindicator : PageIndicatorView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_content, container, false)

        position = arguments!!.getInt("adapter_pos")
        image = MainFragment.posts[position].images

        val bannerPagerAdapter = BannerPagerAdapter(childFragmentManager, image!!)
        imageviewpager = view.findViewById(R.id.pager) as ViewPager
        pageindicator = view.findViewById(R.id.feed) as PageIndicatorView
        imageviewpager!!.adapter = bannerPagerAdapter
        imageviewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
            }

            override fun onPageSelected(position: Int) {
                pageindicator!!.selection = position
            }

            override fun onPageScrollStateChanged(state: Int) {/*empty*/
            }
        })

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