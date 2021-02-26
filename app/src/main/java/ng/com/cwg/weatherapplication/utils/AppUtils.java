package ng.com.cwg.weatherapplication.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 24/02/2021 07:53
 */
public class AppUtils {
    public static void debug(String log) {
        if (log != null) {
            Log.d("Debug response: %s", log);
        }
    }
    public static  String convertKelvinToCelsius(Double kelvin){
        return String.valueOf(Math.round(kelvin - 273));
    }
    public static  String convertDate(long unixDate){
        Date date = new java.util.Date(unixDate*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("E, MMM dd yyyy");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(date).split(",")[0].toLowerCase();
        String returnValue;
        switch (formattedDate){
            case "mon":
                return "Monday";
            case "tue":
                return "Tuesday";
            case "wed":
                return "Wednesday";
            case "thu":
                return "Thursday";
            case "fri":
                return "Friday";
            case "sat":
                return "Saturday";
            case "sun":
                return "Sunday";
            default:
                return "";
        }
    }
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
    public static BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
