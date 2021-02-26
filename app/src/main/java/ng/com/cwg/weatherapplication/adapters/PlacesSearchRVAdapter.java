package ng.com.cwg.weatherapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ng.com.cwg.weatherapplication.R;
import ng.com.cwg.weatherapplication.model.googlePlaces.SearchedPlaces;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 25/04/2020 14:00
 */
public class PlacesSearchRVAdapter extends RecyclerView.Adapter<PlacesSearchRVAdapter.ViewHolder> {

    ArrayList<SearchedPlaces> searchedPlaces;
    Context context;
    int mode;
    private PlacesClickedListener listener;
    public PlacesSearchRVAdapter(ArrayList<SearchedPlaces> searchedPlaces, Context context, int mode) {
        this.searchedPlaces = searchedPlaces;
        this.context = context;
        this.mode = mode;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.places_search_list,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.primaryTV.setText(searchedPlaces.get(position).primaryName);
        holder.secondaryTV.setText(searchedPlaces.get(position).secondaryName);
    }
    @Override
    public int getItemCount() {
        return searchedPlaces.size();
    }
    public void registerClickListener(PlacesClickedListener listener) {
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView primaryTV, secondaryTV;
        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            primaryTV = itemView.findViewById(R.id.primaryPlaceTV);
            secondaryTV = itemView.findViewById(R.id.secondaryPlaceTV);
        }
        @Override
        public void onClick(View view) {
            if (listener != null) {
                try {
                    listener.placeClicked(searchedPlaces.get(ViewHolder.this.getAdapterPosition()),mode);
                } catch (Exception e) {
                    return;
                }
            }

        }
    }

    public interface PlacesClickedListener {
        void placeClicked(SearchedPlaces item, int mode);
    }
}
