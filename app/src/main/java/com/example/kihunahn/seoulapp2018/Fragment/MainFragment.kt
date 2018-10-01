package com.example.kihunahn.seoulapp2018.Fragment

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kihunahn.seoulapp2018.Adapter.PostsAdapter
import com.example.kihunahn.seoulapp2018.CourseDTO
import com.example.kihunahn.seoulapp2018.HomeActivity
import com.example.kihunahn.seoulapp2018.R
import com.example.kihunahn.seoulapp2018.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(){
    var courseRef = FirebaseFirestore.getInstance().collection("posts")
    var courseList = ArrayList<CourseDTO>()
    lateinit var adapter: PostsAdapter

    companion object {
        val posts: ArrayList<Post> = ArrayList()
    }

    //Firebase 로그인한 사용자 정보
    var cur_user = FirebaseAuth.getInstance().currentUser?.email
    private val onItemClickListener = object : PostsAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            val nextFragment = Content2()
            val bundle = Bundle()

            bundle.putSerializable("PictureList", courseList!!.get(position).PictureList)
            bundle.putSerializable("latitude", courseList!!.get(position).lat)
            bundle.putSerializable("longitude", courseList!!.get(position).lng)
            bundle.putSerializable("stampList", courseList!!.get(position).stampList)
            nextFragment.arguments = bundle

            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()

            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            fragmentTransaction.replace(R.id.container, nextFragment)
            //fragmentTransaction.addToBackStack(null)
            HomeActivity.curFragment = 1
            fragmentTransaction.commit()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Getinformation().execute()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_main, container, false)

        //Firebase 로그인한 사용자 정보
        val mAuth = FirebaseAuth.getInstance()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val resourceIDs = intArrayOf(R.drawable.stamp01, R.drawable.stamp02, R.drawable.stamp03, R.drawable.stamp04, R.drawable.stamp05)
        for(i in 1..5)
            posts.add(Post(cur_user!!.substringBeforeLast("@") + " 의 여행", "이곳에 여행에 대해 한줄 요약", resourceIDs))

        adapter = PostsAdapter(courseList, childFragmentManager)
        adapter.setOnItemClickListener(onItemClickListener)
        newsfeed?.layoutManager = LinearLayoutManager(activity)
        newsfeed?.adapter = adapter
    }

    inner class Getinformation : AsyncTask<String, String, String>() {
        var asyncDialog: ProgressDialog = ProgressDialog(context!!)
        override fun onPreExecute() {
            // Before doInBackground
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            asyncDialog.setMessage("로딩중입니다..")

            // show dialog
            asyncDialog.show()
        }

        override fun doInBackground(vararg urls: String?): String {
            Log.e("ww","doInBackground1")
            try {
                try {
                    Log.e("ww","doInBackground2")

                    courseRef.addSnapshotListener(EventListener<QuerySnapshot> { snapshots, e ->
                        if (e != null)
                            return@EventListener

                        for(dc in snapshots!!.documentChanges) {
                            when(dc.type) {
                                DocumentChange.Type.ADDED -> {
                                    var courseDTO = dc.document.toObject(CourseDTO::class.java)
                                    courseDTO.postId = dc.document.id

                                    var isExist = false

                                    courseList!!.forEach {
                                        if (it.CourseName.equals(courseDTO.CourseName) && it.userName.equals(courseDTO.userName))
                                            isExist = true
                                    }

                                    if (!isExist) {
                                        //PlaceData.placeNameArray.add(courseDTO.CourseName!!)
                                        courseList!!.add(courseDTO)
                                        //PlaceData.placeArray.add(PictureDTO(courseDTO.CourseName, courseDTO.PictureList))
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                                DocumentChange.Type.MODIFIED -> {
                                    var courseDTO = dc.document.toObject(CourseDTO::class.java)
                                    courseDTO.postId = dc.document.id

                                    var toModify = -1

                                    for(idx in 0..courseList.size-1) {
                                        if (courseList.get(idx).postId.equals(courseDTO.postId))
                                            toModify = idx
                                    }

                                    if (toModify != -1) {
                                        courseList.set(toModify, courseDTO)

                                        //PlaceData.placeNameArray.add(courseDTO.CourseName!!)
                                        //courseList!!.add(courseDTO)
                                        //PlaceData.placeArray.add(PictureDTO(courseDTO.CourseName, courseDTO.PictureList))
                                        adapter.notifyDataSetChanged()
                                    }
                                }

                                DocumentChange.Type.REMOVED -> {
                                    var courseName = dc.document.data.getValue("courseName") as String
                                    var userName = dc.document.data.getValue("userName") as String

                                    var toRemove = -1

                                    for(idx in 0..courseList!!.size-1) {
                                        if(courseList!!.get(idx).CourseName.equals(courseName) && courseList!!.get(idx).userName.equals(userName)) {
                                            toRemove = idx
                                            break;
                                        }
                                    }

                                    if(toRemove != -1) {
                                        courseList!!.remove(courseList!!.get(toRemove))
                                        adapter.notifyDataSetChanged()
                                    }
                                }
                            }
                        }
                    })
                } catch (ex: Exception) {
                }
                //publishProgress(inString)
            } catch (ex: Exception) {

            } finally {

            }
            return "complete"
        }

        override fun onProgressUpdate(vararg values: String?) {
            Log.e("update","onProgressUpdate")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if(result.equals("complete"))
                asyncDialog.dismiss()
        }
    }

}