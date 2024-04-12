package com.example.szlaki

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            val fragment = DetailFragment.newInstance(id)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerViewDetail, fragment)
            transaction.commit()
        }
    }
}