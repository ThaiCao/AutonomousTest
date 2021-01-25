package com.app.autonomoustesting.features.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.autonomoustesting.R
import com.app.autonomoustesting.shared.ui.recyclerView.OnItemClickListener
import com.app.autonomoustesting.shared.utils.DateUtil
import com.app.autonomoustesting.shared.utils.MathUtil
import com.app.autonomoustesting.shared.utils.extensions.gone
import com.app.autonomoustesting.shared.utils.extensions.visible
import kotlinx.android.synthetic.main.view_weather_item_large.view.*
import java.util.*

class DailyWeatherViewHolder (
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(dailyWeather: DailyWeatherModel, clickListener: OnItemClickListener?) {

        itemView.tvDate?.text =  DateUtil().toFormatDateToString(Date(dailyWeather.dateTime*1000), DateUtil.detailedMonthDayFormat)
        itemView.tvDay?.text =  DateUtil().toFormatDateToString(Date(dailyWeather.dateTime*1000), DateUtil.dayFormat)
        itemView.tvTemperature?.text = itemView.resources.getString(R.string.weather_temperature).format(
            MathUtil().roundOffDecimal(dailyWeather.temp.day -273.15))

        if (dailyWeather.weathers != null && dailyWeather.weathers.size >0) {
            itemView.tvDescription?.text = dailyWeather.weathers[0].description
            itemView.tvDescription?.visible()
        } else {
            itemView.tvDescription?.gone()
        }

        itemView.rootConstraintLayout?.setOnClickListener {

        }
    }

}