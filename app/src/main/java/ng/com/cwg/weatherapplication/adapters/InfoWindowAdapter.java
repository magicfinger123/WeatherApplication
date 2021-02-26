package ng.com.cwg.weatherapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import ng.com.cwg.weatherapplication.R;


/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 26/04/2020 12:45
 */
public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private View view;

    private FragmentActivity myContext;

    public InfoWindowAdapter(FragmentActivity aContext) {
        this.myContext = aContext;

        LayoutInflater inflater = (LayoutInflater) myContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.mapinfowindow, null);
    }

    @Override
    public View getInfoContents(Marker marker) {

        if (marker != null
                && marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
            marker.showInfoWindow();
        }
        return null;
    }

    @Override
    public View getInfoWindow(final Marker marker) {

        final String title = marker.getTitle();
        final TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            titleUi.setText(title);
        } else {
            titleUi.setText("");
            titleUi.setVisibility(View.GONE);
        }

        final String snippet = marker.getSnippet();
        final TextView snippetUi = ((TextView) view
                .findViewById(R.id.snippet));

        if (snippet != null) {

            String[] SnippetArray = snippet.split(",");


            snippetUi.setText(SnippetArray[0]);
        } else {
            snippetUi.setText("");
        }

        return view;
    }
}