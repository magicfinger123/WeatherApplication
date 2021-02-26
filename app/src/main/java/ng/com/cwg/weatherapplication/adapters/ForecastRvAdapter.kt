package ng.com.cwg.weatherapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ng.com.cwg.weatherapplication.R
import ng.com.cwg.weatherapplication.model.weather.DayAndTemperature
import ng.com.cwg.weatherapplication.utils.AppUtils
import java.util.*
/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 18/06/2020 08:10
 */
class ForecastRvAdapter(
    private var context: Context,
    private var dayAndTemperatures: ArrayList<DayAndTemperature>
) : RecyclerView.Adapter<ForecastRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.forecast_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temperature = AppUtils.convertKelvinToCelsius(
            java.lang.Double.valueOf(
                dayAndTemperatures[position].temp
            )
        )
        holder.dayOfTheWeek.text = dayAndTemperatures[position].day
        val temp = temperature+context.resources.getString(R.string.celsius_symbol)
        holder.temperatureTextView.text = temp
        when (dayAndTemperatures[position].weatherType.toLowerCase(Locale.ROOT)) {
            "clear" -> {
                holder.weatherIcon.setImageResource(R.drawable.clear)
                holder.weatherIcon.setImageResource(R.drawable.partlysunny)
                holder.weatherIcon.setImageResource(R.drawable.rain)
            }
            "cloud" -> {
                holder.weatherIcon.setImageResource(R.drawable.partlysunny)
                holder.weatherIcon.setImageResource(R.drawable.rain)
            }
            "rain" -> holder.weatherIcon.setImageResource(R.drawable.rain)
        }
    }

    override fun getItemCount(): Int {
        return dayAndTemperatures.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dayOfTheWeek: TextView = itemView.findViewById(R.id.dayOfTheWeek)
        var temperatureTextView: TextView = itemView.findViewById(R.id.temperatureTextView)
        var weatherIcon: ImageView = itemView.findViewById(R.id.imageWeatherIcon)

    }
}