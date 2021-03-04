package com.jovandy.testrs.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jovandy.testrs.R
import com.jovandy.testrs.api.respone.listdata.DataItem

class MainAdapter(private val dataItem: List<DataItem?>, val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var clickListener: ClickListener? = null
    var row_index = -1
    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dataItem[position]!!, context)
    }

    override fun getItemCount(): Int {
        return dataItem.size
    }
    fun getIdItem(position: Int): Int? {
        // return if (mDataSet != null) mDataSet[position] else null
        return dataItem?.get(position)?.id
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bindItems(dataItem: DataItem, context: Context) {
            val name = itemView.findViewById<TextView>(R.id.nama)
            val lat = itemView.findViewById<TextView>(R.id.lat)
            val longt = itemView.findViewById<TextView>(R.id.longt)
            val id = itemView.findViewById<TextView>(R.id.id)
            name.text = dataItem.title
            lat.text = "Lat : "+dataItem.lat
            longt.text = "Long : "+dataItem.jsonMemberLong
            id.text = dataItem.id.toString()
        }

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }
        override fun onClick(v: View?) {
            if (v != null) {
                clickListener?.onItemClick(v, adapterPosition)
            }
        }
    }
    interface ClickListener {
        fun onItemClick(v: View, position: Int)
    }
}
