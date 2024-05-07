package com.example.szlaki
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startAnimation()
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
    private fun startAnimation() {

        val rockImageView = findViewById<ImageView>(R.id.rock_image_view)

        // Create a shake animation
        val shakeAnimatorY = ObjectAnimator.ofFloat(rockImageView, View.TRANSLATION_Y, 0f, 100f, -100f, 0f)
        shakeAnimatorY.duration = 500 // Duration in milliseconds
        shakeAnimatorY.repeatCount = 5 // Repeat the shake animation 5 times

        // Create a shake animation for X-axis
        val shakeAnimatorX = ObjectAnimator.ofFloat(rockImageView, View.TRANSLATION_X, 0f, 100f, -100f, 0f)
        shakeAnimatorX.duration = 500 // Duration in milliseconds
        shakeAnimatorX.repeatCount = 5 // Repeat the shake animation 5 times

        // Create an AnimatorSet to play the X and Y shake animations together
        val shakeAnimatorSet = AnimatorSet()
        shakeAnimatorSet.playTogether(shakeAnimatorY, shakeAnimatorX)

        // Create a fade out animation
        val fadeOutAnimator = ObjectAnimator.ofFloat(rockImageView, View.ALPHA, 1f, 0f)
        fadeOutAnimator.duration = 2000 // Duration in milliseconds

        // Start the animations in sequence
        shakeAnimatorSet.start()
        shakeAnimatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                fadeOutAnimator.start()
            }
        })
    }


}