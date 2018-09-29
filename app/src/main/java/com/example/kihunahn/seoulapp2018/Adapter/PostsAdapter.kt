package com.example.kihunahn.seoulapp2018.Adapter


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kihunahn.seoulapp2018.CourseDTO
import com.example.kihunahn.seoulapp2018.Fragment.BlankFragment
import com.example.kihunahn.seoulapp2018.Fragment.MainFragment.Companion.posts
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.like.LikeButton
import com.like.OnLikeListener
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.post_row.view.*
import java.util.*
import kotlin.collections.ArrayList


class PostsAdapter(val courseList: ArrayList<CourseDTO>, val fragmentmanager : FragmentManager) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    var courseRef = FirebaseFirestore.getInstance().collection("posts")
    internal var mViewPagerState = HashMap<Int, Int>()

    fun getUserId() : String {
        var userEmail = FirebaseAuth.getInstance().currentUser!!.email
        var ret = userEmail!!.substring(0, userEmail!!.indexOf('@'))
        return ret
    }

    override fun getItemCount() = courseList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return ViewHolder(view)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        mViewPagerState[holder.adapterPosition] = holder.imageviewpager.currentItem
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("position",position.toString())

        var courseDTO = courseList[position]

        val bannerPagerAdapter = BannerPagerAdapter(fragmentmanager, courseList[position].PictureList!!, position)
        holder.imageviewpager.adapter = bannerPagerAdapter
        holder.imageviewpager.id = position +1

        if (mViewPagerState.containsKey(position)) {
            holder.imageviewpager.currentItem = mViewPagerState[position]!!
        }


        holder.imageviewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
            }

            override fun onPageSelected(position: Int) {
                holder.pageindicator.selection = position
            }

            override fun onPageScrollStateChanged(state: Int) {/*empty*/
            }
        })
        holder.pageindicator.count = courseDTO.PictureList!!.size
        holder.pageindicator.selection = 0

        holder.username.text = courseDTO.userName
        holder.textfeed.text = courseDTO.CourseName

        holder.like.isLiked = courseDTO.like!!.contains(getUserId())
        holder.count_like.text = "좋아요 ${courseDTO.like!!.size}"+"개"

        holder.like.setOnLikeListener(object : OnLikeListener {
            var userName = getUserId()
            override fun liked(likeButton: LikeButton) {
                if(!courseDTO.like!!.contains(userName)) {
                    courseDTO.like!!.add(userName)
                    courseRef.document(courseDTO.postId!!).update("like", courseDTO.like)
                }
                holder.count_like.text = "좋아요 ${courseDTO.like!!.size}"+"개"
            }

            override fun unLiked(likeButton: LikeButton) {
                if(courseDTO.like!!.contains(userName)) {
                    courseDTO.like!!.remove(userName)
                    courseRef.document(courseDTO.postId!!).update("like", courseDTO.like)
                }
                holder.count_like.text = "좋아요 ${courseDTO.like!!.size}"+"개"
            }
        })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.username)
        val textfeed: TextView = itemView.findViewById(R.id.textfeed)
        val pageindicator : PageIndicatorView = itemView.photofeed
        val imageviewpager : ViewPager = itemView.imageVP
        val like : LikeButton = itemView.like_button
        val count_like : TextView = itemView.rate
    }

    inner class BannerPagerAdapter(var fm: FragmentManager, var imagesList: ArrayList<String>, var Position:Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return BlankFragment.getInstance(0, Position)
        }

        override fun getCount(): Int {
            return imagesList.size
        }
    }
}
