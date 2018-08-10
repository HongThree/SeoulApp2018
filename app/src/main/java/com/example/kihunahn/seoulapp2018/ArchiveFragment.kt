package com.example.kihunahn.seoulapp2018

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ArchiveFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_archive, container, false)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var cur_user = FirebaseAuth.getInstance().currentUser?.uid
        FirebaseFirestore.getInstance().collection(cur_user.toString()).get().addOnSuccessListener { querySnapshot ->
            // 이 유저에게 저장 된 여행의 개수 출력 됨!!
            Toast.makeText(activity, querySnapshot.size().toString(), Toast.LENGTH_LONG).show()
        }
    }

    class ReadRecyclerViewAdapter(initList: ArrayList<PositionDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var list: ArrayList<PositionDTO>? = initList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_recyclerview, parent, false)
            return CustomViewHolder(view)
        }

        class CustomViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
            var textview_course_name = view!!.findViewById<TextView>(R.id.textView_course_name)
            var textview_position_count = view!!.findViewById<TextView>(R.id.textView_position_count)
        }

        override fun getItemCount(): Int {
            return list!!.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var customViewHolder = holder as CustomViewHolder
            customViewHolder.textview_course_name.text = list!!.get(position).toString()
            customViewHolder.textview_position_count.text = list!!.get(position).lat!!.size.toString()
        }

    }
}