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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(){


    //Firebase 로그인한 사용자 정보
    var cur_user = FirebaseAuth.getInstance().currentUser?.email


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_main, container, false)

        //Firebase 로그인한 사용자 정보
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.getCurrentUser()

        //Log.e("check", Server.course_la1.toString())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val posts: ArrayList<Post> = ArrayList()
        val resourceIDs = intArrayOf(R.drawable.stamp01, R.drawable.stamp02, R.drawable.stamp03, R.drawable.stamp04, R.drawable.stamp05)
        for(i in 1..5){
            posts.add(Post(cur_user!!.substringBeforeLast("@") + " 의 여행", "이곳에 여행에 대해 한줄 요약", resourceIDs))
        }

        newsfeed?.layoutManager = LinearLayoutManager(activity)
        newsfeed?.adapter = PostsAdapter(posts,fragmentManager!!)

    }


}