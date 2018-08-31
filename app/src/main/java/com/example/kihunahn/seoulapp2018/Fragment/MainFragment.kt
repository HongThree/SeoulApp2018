package com.example.kihunahn.seoulapp2018.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.Adapter.PostsAdapter
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.model.Post
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posts: ArrayList<Post> = ArrayList()
        for(i in 1..100){
            posts.add(Post("sangwon" + i, "text here","https://picsum.photos/600/300?random&" + i))
        }

        newsfeed?.layoutManager = LinearLayoutManager(activity)
        newsfeed?.adapter = PostsAdapter(posts, activity)

    }



}