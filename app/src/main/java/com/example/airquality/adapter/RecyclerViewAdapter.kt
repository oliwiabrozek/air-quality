package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.airquality.R
import com.example.airquality.model.City

interface AdapterOnClick {
  fun onClick(item: TextView)
}

class RecyclerViewAdapter(val cityList: MutableList<City>, val adapterOnClick: AdapterOnClick) :
  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_city, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int = cityList.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    if (cityList[position] != null) {
      holder.idTextView.text = cityList[position].id.toString()
      holder.nameTextView.text = cityList[position].name
      holder.setItem()
    }
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val idTextView: TextView = itemView.findViewById(R.id.city_id)
    val nameTextView: TextView = itemView.findViewById(R.id.city_name)

    fun setItem() {
      idTextView.setOnClickListener {
        idTextView.setOnClickListener {
          adapterOnClick.onClick(idTextView)
        }
      }
    }

  }
}