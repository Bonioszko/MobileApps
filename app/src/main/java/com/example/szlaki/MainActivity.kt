package com.example.szlaki

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(actionBar)


//        val listFragment = ListFragment()
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