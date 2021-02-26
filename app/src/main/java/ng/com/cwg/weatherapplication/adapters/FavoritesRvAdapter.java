package ng.com.cwg.weatherapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ng.com.cwg.weatherapplication.R;
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 25/02/2021 18:19
 */
public  class FavoritesRvAdapter extends RecyclerView.Adapter<FavoritesRvAdapter.ViewHolder> {

    Context context;
    ArrayList<SaveWeatherInfoEntity> saveWeatherInfoEntities;

    public FavoritesRvAdapter(Context context, ArrayList<SaveWeatherInfoEntity> saveWeatherInfoEntity) {
        this.context = context;
        this.saveWeatherInfoEntities = saveWeatherInfoEntity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(saveWeatherInfoEntities.get(position).getTitle());
        String info = saveWeatherInfoEntities.get(position).getWeatherType()+" "+saveWeatherInfoEntities.get(position).getAverageTemperature();
        String temperature = saveWeatherInfoEntities.get(position).getAverageTemperature();
        holder.info.setText(info);
        holder.currentTempTextView.setText(temperature);
        switch (saveWeatherInfoEntities.get(position).getWeatherType().toLowerCase()){
            case "clouds":
                holder.weatherIcon.setImageResource(R.drawable.partlysunny);
                break;
            case "rain":
                holder.weatherIcon.setImageResource(R.drawable.rain);
                break;
            default:
                holder.weatherIcon.setImageResource(R.drawable.clear);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return saveWeatherInfoEntities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView weatherIcon;
        TextView title, info;
        TextView currentTempTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherIcon  = itemView.findViewById(R.id.weatherIcon);
            title  = itemView.findViewById(R.id.title);
            info  = itemView.findViewById(R.id.info);
            currentTempTextView  = itemView.findViewById(R.id.currentTempTextView);
        }
    }
}
