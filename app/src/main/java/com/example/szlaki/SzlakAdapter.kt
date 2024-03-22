package com.example.szlaki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SzlakAdapter(context: Context, szlaks: Array<Szlak>) : ArrayAdapter<Szlak>(context, android.R.layout.simple_list_item_1, szlaks) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)

        val szlak = getItem(position)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = szlak?.name

        return view
    }
}
