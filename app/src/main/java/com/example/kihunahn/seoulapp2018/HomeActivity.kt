package com.example.kihunahn.seoulapp2018

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.Adapter.MenuAdapter
import com.example.kihunahn.seoulapp2018.Fragment.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.view_header.*
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.util.*

class HomeActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {
    private var mMenuAdapter: MenuAdapter? = null
    private var mViewHolder: ViewHolder? = null
    private var mTitles: ArrayList<String> = ArrayList()
    companion object {
        var curFragment : Int = 0
    }
    private var userInfo = UserInfoDTO(getUserId())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Server(this).setting()
        updateCourseList()
        mTitles = ArrayList<String>(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))
        // Initialize the views
        mViewHolder = ViewHolder()
        // Handle toolbar actions
        handleToolbar()
        // Handle menu actions
        handleMenu()
        // Handle drawer actions
        handleDrawer()
        // Show main fragment in container
        goToFragment(MainFragment(), false)
        mMenuAdapter!!.setViewSelected(0, true)
        title = mTitles[0]

        //Firebase 로그인한 사용자 정보
        var cur_user = FirebaseAuth.getInstance().currentUser?.email

        header_user.text = cur_user!!.substringBeforeLast("@")

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)) {
            } else {
            }
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 0)
        }

        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)) {
            } else {
            }
            ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0){
            // requestPermission의 두번째 매개변수는 배열이므로 아이템이 여러개 있을 수 있기 때문에 결과를 배열로 받는다.
            // 해당 예시는 요청 퍼미션이 한개 이므로 i=0 만 호출한다.
            if(grantResults[0] == 0){
                //해당 권한이 승낙된 경우.
            }else{
                //해당 권한이 거절된 경우.
            }
        }
    }


    fun getUserId() : String {
        var userEmail = FirebaseAuth.getInstance().currentUser!!.email
        var ret = userEmail!!.substring(0, userEmail!!.indexOf('@'))
        return ret
    }

    //뒤로가기
    private var time: Long = 0
    override fun onBackPressed() {

        if(supportFragmentManager.backStackEntryCount != 0) {
            var temp:String = supportFragmentManager.fragments.toString().substring(1,5)
            if(temp=="Make"){
                val builder = AlertDialog.Builder(this)
                builder.setMessage("저장 안 할꺼야?")
                builder.setPositiveButton("예", DialogInterface.OnClickListener(){ DialogInterface, which ->
                    supportFragmentManager.popBackStack()
                })
                builder.show()
            }
            else{
                supportFragmentManager.popBackStack()
            }
        }
        else {
            if(curFragment != 0) {
                curFragment = 0
                onOptionClicked(curFragment, false)
            }
            else {
                if (System.currentTimeMillis() - time >= 2000) {
                    time = System.currentTimeMillis()
                    Toast.makeText(applicationContext, "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show()
                } else if (System.currentTimeMillis() - time < 2000) {

                    moveTaskToBack(true)
                    finish()
                    android.os.Process.killProcess(android.os.Process.myPid())
                }
            }
        }
    }

    fun updateCourseList() {
        var id = userInfo.id
        FirebaseFirestore.getInstance().collection(id).get().addOnSuccessListener { querySnapshot ->
            // 이 유저에게 저장 된 여행의 개수 출력 됨!!
            //Toast.makeText(activity, querySnapshot.documents.size.toString(), Toast.LENGTH_LONG).show()
            //querySnapshot.documents.size
            querySnapshot.forEach {
                //[lat,lon]
                //Toast.makeText(activity, it.data.keys.toString(), Toast.LENGTH_LONG).show()
                // 이게 실제 여행명 받아오기
                //Toast.makeText(this, it.id, Toast.LENGTH_SHORT).show()
                var title = it.id
                if(title[title.length-1] == '_') {
                    userInfo.courseList.get(userInfo.courseList.size-1).pictures!!.lat = it.data.get("lat") as ArrayList<Double>
                    userInfo.courseList.get(userInfo.courseList.size-1).pictures!!.lon = it.data.get("lon") as ArrayList<Double>
                    userInfo.courseList.get(userInfo.courseList.size-1).pictures!!.uri = it.data.get("uri") as ArrayList<String>
                }
                else {
                    var course = CourseDTO(title)
                    course.positions.lat = it.data.get("lat") as ArrayList<Double>
                    course.positions.lon = it.data.get("lon") as ArrayList<Double>
                    userInfo.courseList.add(course)
                }
            }
        }
    }

    private fun handleToolbar() {
        setSupportActionBar(mViewHolder!!.mToolbar)
    }

    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(this,
                mViewHolder!!.mDuoDrawerLayout,
                mViewHolder!!.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        mViewHolder!!.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()

    }

    private fun handleMenu() {
        mMenuAdapter = MenuAdapter(mTitles)

        mViewHolder!!.mDuoMenuView.setOnMenuClickListener(this)
        mViewHolder!!.mDuoMenuView.adapter = mMenuAdapter
    }

    override fun onFooterClicked() {
        //       Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show()
        FirebaseAuth.getInstance().signOut()
        finish()

    }

    override fun onHeaderClicked() {
        Toast.makeText(this, "사용자 정보입니다", Toast.LENGTH_SHORT).show()
    }

    private fun goToFragment(fragment: Fragment, addToBackStack: Boolean) {
        var transaction = supportFragmentManager.beginTransaction()

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.container,fragment).commit()
    }

    override fun onOptionClicked(position: Int, objectClicked: Any) {
        curFragment = position
        // Set the toolbar title
        title = mTitles[curFragment]

        // Set the right options selected
        mMenuAdapter!!.setViewSelected(curFragment, true)

        supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        // Navigate to the right fragment
        when (curFragment) {
            0 -> goToFragment(MainFragment(), false)
            1 -> goToFragment(MyCourseFragment(), false)
            2 -> goToFragment(InfoFragment(), false)
            3 ->  goToFragment(CourseFragment(), false)
            4 -> goToFragment(StampFragment(), false)
            5 -> goToFragment(AboutAppFragment(), false)

            else -> goToFragment(MainFragment(), false)
        }

        // Close the drawer
        mViewHolder!!.mDuoDrawerLayout.closeDrawer()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val frg = supportFragmentManager.findFragmentById(R.id.container)
        frg?.onActivityResult(requestCode, resultCode, data)
    }

    private inner class ViewHolder internal constructor() {
        val mDuoDrawerLayout: DuoDrawerLayout
        val mDuoMenuView: DuoMenuView
        val mToolbar : android.support.v7.widget.Toolbar

        init {
            mDuoDrawerLayout = drawer
            mDuoMenuView = mDuoDrawerLayout.menuView as DuoMenuView
            mDuoMenuView.setBackground(R.drawable.example)
            mToolbar = toolbar
        }
    }
}