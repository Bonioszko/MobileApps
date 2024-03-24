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
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = TrailAdapter(inflater.context, trails)

        listView = view.findViewById(R.id.listview_item)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            onListItemClicked(position, requireActivity().layoutInflater)
        }

        return view
    }

    private fun onListItemClicked(position: Int, inflater: LayoutInflater) {
        val detailContainer = activity?.findViewById<View>(R.id.detail_container)
        if (detailContainer != null) {
            swapDetailFragment(position)
        } else {
            openNewFragment(position, inflater)
        }
    }

    // Handles tablet layout
    private fun swapDetailFragment(position: Int) {
        val trail = trails[position]
        val detailFragment = DetailFragment.newInstance(trail.id)

        val transaction2 = requireActivity().supportFragmentManager.beginTransaction()
        transaction2.replace(R.id.detail_container, detailFragment)

        // Add stack entry to handle going back to previous details
        transaction2.addToBackStack(null)
        transaction2.commit()
    }

    // Handles phone layout
    private fun openNewFragment(position: Int, inflater: LayoutInflater) {
        val trail = trails[position]
        val intent = Intent(inflater.context, DetailActivity::class.java)
        intent.putExtra("id", trail.id)
        startActivity(intent)
    }
}