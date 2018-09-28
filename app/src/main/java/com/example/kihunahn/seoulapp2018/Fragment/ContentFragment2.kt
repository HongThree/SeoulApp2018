package com.example.kihunahn.seoulapp2018.Fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.kihunahn.seoulapp2018.R

class ContentFragment2 : Fragment(){
    private var imageFile: String? = ""
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageFile = arguments!!.get("image_file").toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<View>(R.id.viewpager) as ImageView

        val o = BitmapFactory.Options()
        o.inSampleSize = 1
        //o.inDither = false
        bitmap = BitmapFactory.decodeFile(imageFile!!.substring(imageFile!!.indexOf(':')+1))
        imageView.setImageBitmap(bitmap)

    }


    override fun onDestroy() {
        super.onDestroy()
        bitmap!!.recycle()
        bitmap = null
    }

    companion object {
        fun getInstance(resourceID: String): ContentFragment2 {
            val f = ContentFragment2()
            val args = Bundle()
            args.putSerializable("image_file", resourceID)
            f.arguments = args
            return f
        }
    }

}