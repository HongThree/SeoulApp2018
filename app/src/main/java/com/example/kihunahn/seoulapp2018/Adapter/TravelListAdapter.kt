package com.example.kihunahn.seoulapp2018.Adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.kihunahn.seoulapp2018.PlaceData
import com.example.kihunahn.seoulapp2018.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_places.view.*
import java.util.*


class TravelListAdapter(private var context: Context) : RecyclerView.Adapter<TravelListAdapter.ViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun getItemCount() = PlaceData.placeList().size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_places, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val place = PlaceData.placeList()[position]
        val place = PlaceData.placeArray.get(position)
        holder.itemView.placeName.text = place.name
        //Picasso.get().load(place.getImageResourceId(context)).into(holder.itemView.placeImage)
        //Picasso.get().load("file:///storage/emulated/0/Android/data/com.example.kihunahn.seoulapp2018/files/Pictures/img3.jpg").into(holder.itemView.placeImage)
        //Log.d("img", place.uris!!.size.toString())
        if(place.uris!!.size != 0) {
            var fileName = place.uris!!.get(Random().nextInt(place.uris!!.size))
            Picasso.get().load(fileName).into(holder.itemView.placeImage)
        }
        else {
            Picasso.get().load(getDrawble("default_img")).into(holder.itemView.placeImage)
        }
        val photo = BitmapFactory.decodeResource(context.resources, getDrawble("default_img"))
        Palette.from(photo).generate { palette ->
            val bgColor = palette!!.getMutedColor(ContextCompat.getColor(context, android.R.color.black))
            holder.itemView.placeNameHolder.setBackgroundColor(bgColor)
        }
    }
    fun getDrawble(fileName : String ) : Int {
        return context!!.resources.getIdentifier(fileName, "drawable", context!!.packageName)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        init {
            itemView.placeHolder.setOnClickListener(this)
            itemView.placeHolder.setOnLongClickListener(this)
        }

        override fun onClick(view: View) = itemClickListener.onItemClick(itemView, adapterPosition)
        override fun onLongClick(view: View): Boolean {
            itemClickListener.onItemLongClick(itemView, adapterPosition)
            return true
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}