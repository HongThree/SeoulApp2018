package com.example.kihunahn.seoulapp2018.Adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.model.Post
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.post_row.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_row.view.*

class PostsAdapter(val posts: ArrayList<Post>, val context: FragmentActivity?) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = posts[position].username
        holder.textfeed.text = posts[position].text
//        Picasso.get().load(posts[position].photo).into(holder.photo)
        holder.photo.setViewPager(holder.imageVP)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.username)
        val textfeed: TextView = itemView.findViewById(R.id.textfeed)
        val photo :PageIndicatorView = itemView.findViewById(R.id.photofeed)
        val imageVP = itemView.imageVP
    }
}