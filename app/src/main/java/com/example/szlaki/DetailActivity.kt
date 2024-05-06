package com.example.szlaki

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = findViewById<Toolbar>(R.id.action_bar)
        setSupportActionBar(actionBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val fab = findViewById<View>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this,"photo" , Toast.LENGTH_SHORT).show();
        }



        val id = intent.getIntExtra("id", -1)
        if (id != -1) {
            val fragment = DetailFragment.newInstance(id)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerViewDetail, fragment)
            transaction.commit()
        }
    }
}