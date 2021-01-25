package com.app.autonomoustesting.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.autonomoustesting.R
import com.app.autonomoustesting.shared.ui.recyclerView.RecyclerViewAdapterBase

class DailyWeatherAdapter  : RecyclerViewAdapterBase<DailyWeatherModel, DailyWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.view_weather_item_large, parent, false)
        return DailyWeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

}