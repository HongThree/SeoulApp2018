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
import com.example.kihunahn.seoulapp2018.Fragment.BlankFragment
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.model.Post
import com.like.LikeButton
import com.like.OnLikeListener
import com.rd.PageIndicatorView
import kotlinx.android.synthetic.main.post_row.view.*
import java.util.*




class PostsAdapter(val posts: ArrayList<Post>, val fragmentmanager : FragmentManager) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    internal var mViewPagerState = HashMap<Int, Int>()

    override fun getItemCount() = posts.size

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
        val bannerPagerAdapter = BannerPagerAdapter(fragmentmanager, posts[position].images, position)
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
        holder.pageindicator.count = 5
        holder.pageindicator.selection = 0

        holder.username.text = posts[position].username
        holder.textfeed.text = posts[position].text

        holder.like.setOnLikeListener(object : OnLikeListener {
            var cnt = 0
            override fun liked(likeButton: LikeButton) {
                cnt++
                holder.count_like.text = "좋아요 $cnt"+"개"
            }

            override fun unLiked(likeButton: LikeButton) {
                cnt--
                holder.count_like.text = "좋아요 $cnt"+"개"
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

    inner class BannerPagerAdapter(var fm: FragmentManager, var imagesList: IntArray, var Position:Int) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return BlankFragment.getInstance(imagesList[position],Position)
        }

        override fun getCount(): Int {
            return imagesList.size
        }
    }
}
