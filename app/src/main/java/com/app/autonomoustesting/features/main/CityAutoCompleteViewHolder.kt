package com.app.autonomoustesting.features.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.autonomoustesting.shared.ui.recyclerView.OnItemClickListener
import kotlinx.android.synthetic.main.view_city_item.view.*

class CityAutoCompleteViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView)  {

    fun bind(cityModel: CityModel, clickListener: OnItemClickListener?) {
        itemView.tvCity?.text = cityModel.cityName
        itemView.rootConstraintLayout?.setOnClickListener {
            clickListener?.onItemClicked(cityModel.cityName!!)
        }
    }
}