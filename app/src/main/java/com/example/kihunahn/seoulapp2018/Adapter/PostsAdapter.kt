package com.example.kihunahn.seoulapp2018.Adapter

import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.model.Post
import com.like.LikeButton
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.post_row.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_row.view.*
import com.like.OnLikeListener



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
//        holder.photo.setViewPager(holder.imageVP)


        holder.like.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show()
            }

            override fun unLiked(likeButton: LikeButton) {
                Toast.makeText(context, "Disliked!", Toast.LENGTH_SHORT).show()
            }
        })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val profile : ImageView = itemView.profile_img
        val username: TextView = itemView.findViewById(R.id.username)
        val textfeed: TextView = itemView.findViewById(R.id.textfeed)
        val pageindicator : PageIndicatorView = itemView.photofeed
        val imageviewpager : ViewPager = itemView.imageVP
        val like : LikeButton = itemView.like_button
        val count_like : TextView = itemView.rate

    }
}