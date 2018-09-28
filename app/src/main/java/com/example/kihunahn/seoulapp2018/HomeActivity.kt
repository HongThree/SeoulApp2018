package com.example.kihunahn.seoulapp2018

import android.Manifest
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Server(this).setting()

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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }

        //로그아웃
        logout.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("알림")
            builder.setMessage("로그아웃 하시겠습니까?")
            builder.setNegativeButton("취소",null)
            builder.setPositiveButton("로그아웃") { arg0, arg1 ->
                FirebaseAuth.getInstance().signOut()
                finish()
            }
            builder.show()
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
//        FirebaseAuth.getInstance().signOut()
//        finish()

    }

    override fun onHeaderClicked() {
//        Toast.makeText(this, "사용자 정보입니다", Toast.LENGTH_SHORT).show()
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