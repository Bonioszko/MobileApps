package com.example.szlaki

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load


class CustomAdapter(private val dataSet: Array<Trail>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var nameView: TextView

        init {
            imageView = itemView.findViewById(R.id.image)
            nameView = itemView.findViewById(R.id.name)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_element, viewGroup, false)

        return ViewHolder(view)
    }

        // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameView.text = dataSet[position].name
        viewHolder.imageView.load(dataSet[position].thumbnail)
        Log.i("test", dataSet[position].thumbnail)

        viewHolder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, dataSet[position] )
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Trail)
    }

}
