package com.example.szlaki
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//

        val actionBar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(actionBar)
        actionBar.title = getString(R.string.app_name)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val toggle =  ActionBarDrawerToggle(
            this,
            drawer,
            actionBar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawer.addDrawerListener(toggle)


        toggle.syncState()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.drawer_gallery -> {
                    val galleryFragment = PagerFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.list_frag, galleryFragment)
                    transaction.commit()
                }

            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }

        val listFragment = PagerFragment()
        val transaction1 = supportFragmentManager.beginTransaction()
        transaction1.replace(R.id.list_frag, listFragment)
        transaction1.commit()

        // Render detail fragment only if container is present (tablet layout)
        val detailContainer = findViewById<View>(R.id.detail_container)
        if (detailContainer !== null) {
            val detailFragment = DetailFragment.newInstance(-1)
            val transaction2 = supportFragmentManager.beginTransaction()
            transaction2.replace(R.id.detail_container, detailFragment)
            transaction2.commit()
        }
    }





}