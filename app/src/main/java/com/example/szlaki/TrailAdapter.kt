package com.example.szlaki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TrailAdapter(context: Context, trails: Array<Trail>) : ArrayAdapter<Trail>(context, android.R.layout.simple_list_item_1, trails) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)

        val trail = getItem(position)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = trail?.name

        return view
    }
}
