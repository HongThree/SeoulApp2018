package com.example.kihunahn.seoulapp2018.Fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.kihunahn.seoulapp2018.HomeActivity
import com.example.kihunahn.seoulapp2018.R


class BlankFragment : Fragment() {
    private var imageResource: Int = 0
    private var bitmap: Bitmap? = null
    var position :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageResource = arguments!!.getInt("image_source")
        position = arguments!!.getInt("adapter_pos")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<View>(R.id.viewpager_image) as ImageView

        val o = BitmapFactory.Options()
        o.inSampleSize = 1
        //o.inDither = false
        bitmap = BitmapFactory.decodeResource(resources, imageResource, o)
        imageView.setImageBitmap(bitmap)


        imageView.setOnClickListener {
            val savedTrip = Content()
            val args = Bundle()
            args.putInt("adapter_pos", position)

            savedTrip.arguments = args
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.container, savedTrip)
            HomeActivity.curFragment = 1

            fragmentTransaction.commit()

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //bitmap!!.recycle()
        bitmap = null
    }

    companion object {
        fun getInstance(resourceID: Int, pos: Int): BlankFragment {
            val f = BlankFragment()
            val args = Bundle()
            args.putInt("image_source", resourceID)
            args.putInt("adapter_pos", pos)
            f.arguments = args
            return f
        }
    }

}
