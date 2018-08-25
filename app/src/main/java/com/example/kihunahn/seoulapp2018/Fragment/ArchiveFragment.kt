package com.example.kihunahn.seoulapp2018.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.Adapter.TravelListAdapter
import com.example.kihunahn.seoulapp2018.PositionDTO
import com.example.kihunahn.seoulapp2018.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_archive.view.*

class ArchiveFragment : Fragment() {

    lateinit private var staggeredLayoutManager: StaggeredGridLayoutManager
    lateinit private var adapter: TravelListAdapter

    override fun onStart() {
        super.onStart()
    }

    private val onItemClickListener = object : TravelListAdapter.OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
        Toast.makeText(activity, "Clicked " + position, Toast.LENGTH_SHORT).show()
        //val transitionIntent = DetailActivity.newIntent(this@MainActivity, position)
        val placeImage = view.findViewById<ImageView>(R.id.placeImage)
        val placeNameHolder = view.findViewById<LinearLayout>(R.id.placeNameHolder)


        //val navigationBar = findViewById<View>(android.R.id.navigationBarBackground)
        ///val statusBar = findViewById<View>(android.R.id.statusBarBackground)

        val imagePair = Pair.create(placeImage as View, "tImage")
        val holderPair = Pair.create(placeNameHolder as View, "tNameHolder")

        //val navPair = Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME)
        //val statusPair = Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME)


        //val pairs = mutableListOf(imagePair, holderPair, statusPair, toolbarPair)
        val pairs = mutableListOf(imagePair, holderPair)
        /*
        if (navigationBar != null && navPair != null) {
            pairs += navPair
        }
        */
        /*
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,
                *pairs.toTypedArray())
        ActivityCompat.startActivity(this@MainActivity, transitionIntent, options.toBundle())
        */


    }
}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_archive, container, false)

        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        if(view.list_view == null){
            Toast.makeText(activity, "널이야 시발", Toast.LENGTH_LONG).show()
        }

        view.list_view.layoutManager = staggeredLayoutManager

        adapter = TravelListAdapter(activity!!)
        adapter.setOnItemClickListener(onItemClickListener)

        Toast.makeText(activity, "Clicked " + adapter.itemCount, Toast.LENGTH_SHORT).show()

        view.list_view.adapter = adapter

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var cur_user = FirebaseAuth.getInstance().currentUser?.uid
        FirebaseFirestore.getInstance().collection(cur_user.toString()).get().addOnSuccessListener { querySnapshot ->
            // 이 유저에게 저장 된 여행의 개수 출력 됨!!
            //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
            //querySnapshot.documents.size
            querySnapshot.forEach {
                //[lat,lon]
                //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()

                // 이게 실제 여행명 받아오기
                Toast.makeText(activity, it.id, Toast.LENGTH_LONG).show()
            }
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