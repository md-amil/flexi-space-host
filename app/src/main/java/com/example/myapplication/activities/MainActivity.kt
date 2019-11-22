package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R
import com.example.myapplication.fragments.CalendarFragment
import com.example.myapplication.fragments.DashboardFragment
import com.example.myapplication.fragments.EmptyRequestFragment
import com.example.myapplication.utilities.App
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val calenderFragment: Fragment = CalendarFragment()
    private val dashboardFragment: Fragment = DashboardFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active = dashboardFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar:Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.navigation)

        if(!App.loginPrefs.isLoggedIn){
            val intent  = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // if user has no space to show
        else if(false){
            fm.beginTransaction().add(R.id.content, EmptyRequestFragment(), "3").commit()
        }else{
            fm.beginTransaction().add(R.id.content, calenderFragment, "2").hide(calenderFragment)
                .commit()
            fm.beginTransaction().add(R.id.content, dashboardFragment, "1").commit()
            bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }
    override fun onBackPressed() {
        finishAffinity()
    }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_request -> {
                    fm.beginTransaction().hide(active).show(dashboardFragment).commit()
                    active = dashboardFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_calender -> {
                    fm.beginTransaction().hide(active).show(calenderFragment).commit()
                    active = calenderFragment
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_logout-> {
                App.loginPrefs.isLoggedIn = false
                App.loginPrefs.authToken = ""
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}