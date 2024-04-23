package com.example.szlaki

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    private var id: Int? = null
    private var trail: Trail? = null

    private lateinit var stopperFragment: StopperFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
            Log.d("id", id.toString())
            trail = trails.firstOrNull { it.id == id } ?: trails[0]
            Log.d("trail", trail!!.name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Do something with id here...
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // Assuming you have TextViews with the ids: trail_name, trail_description, trail_points
        val nameTextView = view.findViewById<TextView>(R.id.szlak_name)
        val descriptionTextView = view.findViewById<TextView>(R.id.szlak_description)
        val pointsListView = view.findViewById<ListView>(R.id.szlak_list)
        val fab = view.findViewById<View>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(getContext(),"photo" , Toast.LENGTH_SHORT).show();
        }

        nameTextView.text = trail?.name
        descriptionTextView.text = trail?.description
        val adapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, trail?.points ?: arrayOf())        // Set the ArrayAdapter as the ListView's adapter
        pointsListView.adapter = adapter
        stopperFragment = StopperFragment()
        stopperFragment.setTrail(trail)
        childFragmentManager.beginTransaction()
            .replace(R.id.stopper_container, stopperFragment)
            .commit()
        return view
    }

    companion object {
        private const val ARG_ID = "id"

        fun newInstance(id: Int) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }
}