package com.example.kihunahn.seoulapp2018

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.kihunahn.seoulapp2018.Adapter.MenuAdapter
import com.example.kihunahn.seoulapp2018.Fragment.*
import com.example.kihunahn.seoulapp2018.R.id.drawer
import com.example.kihunahn.seoulapp2018.R.id.fragmentHere
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.util.*


class HomeActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {
    private var mMenuAdapter: MenuAdapter? = null
    private var mViewHolder: ViewHolder? = null
    private var mTitles: ArrayList<String> = ArrayList()
    private var curFragment : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Manifest.permission.READ_CALENDAR이 접근 승낙 상태 일때
        } else {
            //Manifest.permission.READ_CALENDAR이 접근 거절 상태 일때
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION)) {
                //사용자가 다시 보지 않기에 체크를 하지 않고, 권한 설정을 거절한 이력이 있는 경우
            } else {
                //사용자가 다시 보지 않기에 체크하고, 권한 설정을 거절한 이력이 있는 경우
            }
            //사용자에게 접근권한 설정을 요구하는 다이얼로그를 띄운다.
            //만약 사용자가 다시 보지 않기에 체크를 했을 경우엔 권한 설정 다이얼로그가 뜨지 않고,
            //곧바로 OnRequestPermissionResult가 실행된다.
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 0)
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



    fun removeCurrentFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        val currentFrag = supportFragmentManager.findFragmentById(R.id.fragmentHere)


        var fragName = "NONE"

        if (currentFrag != null)
            fragName = currentFrag.javaClass.simpleName


        if (currentFrag != null)
            transaction.remove(currentFrag)

        transaction.commit()

    }
    //뒤로가기
    private var time: Long = 0
    override fun onBackPressed() {

        if(supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack()
        }
        else {
            if(curFragment != 0) {
                goToFragment(MainFragment(), false)
                curFragment = 0
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
        FirebaseAuth.getInstance().signOut()
        finish()

    }

    override fun onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show()
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

        // Navigate to the right fragment
        when (curFragment) {
            0 -> goToFragment(MainFragment(), false)
            1 -> goToFragment(MyCourseFragment(), false)
            2 -> goToFragment(InfoFragment(), false)
            3 ->  goToFragment(CourseFragment(), false)
            4 -> goToFragment(StampFragment(), false)

            6 -> goToFragment(AboutAppFragment(), false)

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