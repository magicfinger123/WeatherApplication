package ng.com.cwg.weatherapplication.app;

import android.app.Application;

import com.google.android.libraries.places.api.Places;

import ng.com.cwg.weatherapplication.R;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 25/02/2021 09:43
 */
public  class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_places_api_key));
        }
    }

}
