package com.example.szlaki

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var listView: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listView = view.findViewById(R.id.listview_item)

        val adapter = TrailAdapter(inflater.context, trails)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->

            val trail = trails[position]
            println(trail.id)
            val intent = Intent(inflater.context, DetailActivity::class.java)
            intent.putExtra("id", trail.id)
            startActivity(intent)
        }
        return view
    }


}