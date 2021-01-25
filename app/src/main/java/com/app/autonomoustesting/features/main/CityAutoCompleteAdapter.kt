package com.app.autonomoustesting.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.autonomoustesting.R
import com.app.autonomoustesting.shared.ui.recyclerView.RecyclerViewAdapterBase
import java.util.ArrayList

class CityAutoCompleteAdapter : RecyclerViewAdapterBase<CityModel, CityAutoCompleteViewHolder>() {
    protected var backupData: MutableList<CityModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAutoCompleteViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.view_city_item, parent, false)
        return CityAutoCompleteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityAutoCompleteViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

    fun filter(charText: String) {
        var charText = charText
        items.clear()
        if (charText.isEmpty()) {
            items.addAll(backupData)
        } else {
            for (city in backupData) {
                if (city.cityName!!.contains(charText)) {
                    items.add(city)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun updateBackupData(list: List<CityModel>?) {
        backupData = ArrayList(list)
    }

}