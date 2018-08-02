package com.example.kihunahn.seoulapp2018

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.util.*
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.SubMenu
import com.example.kihunahn.seoulapp2018.R.id.drawer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), DuoMenuView.OnMenuClickListener {
    private var mMenuAdapter: MenuAdapter? = null
    private var mViewHolder: ViewHolder? = null
    private var mTitles: ArrayList<String> = ArrayList()

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
        val transaction = supportFragmentManager.beginTransaction()

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.add(R.id.container, fragment).commit()
    }

    override fun onOptionClicked(position: Int, objectClicked: Any) {
        // Set the toolbar title
        title = mTitles[position]

        // Set the right options selected
        mMenuAdapter!!.setViewSelected(position, true)

        // Navigate to the right fragment
        when (position) {
            1 -> goToFragment(MainFragment(), false)

            3 ->  goToFragment(CourseFragment(), false)
            else -> goToFragment(MainFragment(), false)
        }

        // Close the drawer
        mViewHolder!!.mDuoDrawerLayout.closeDrawer()
    }


    private inner class ViewHolder internal constructor() {
        val mDuoDrawerLayout: DuoDrawerLayout
        val mDuoMenuView: DuoMenuView
        val mToolbar : android.support.v7.widget.Toolbar

        init {
            mDuoDrawerLayout = drawer
            mDuoMenuView = mDuoDrawerLayout.menuView as DuoMenuView
            mToolbar = toolbar
        }
    }
}