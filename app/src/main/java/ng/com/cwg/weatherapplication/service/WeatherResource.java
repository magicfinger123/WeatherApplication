package ng.com.cwg.weatherapplication.service;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 29/08/2020 00:43
 */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class WeatherResource<T> {

    @NonNull
    public final ResponseStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public WeatherResource(@NonNull ResponseStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> WeatherResource<T> successful(@Nullable T data) {
        return new WeatherResource<>(ResponseStatus.SUCCESSFUL, data, null);
    }
    public static<T> WeatherResource<T> error(@NonNull String msg, @Nullable T data) {
        return new WeatherResource<>(ResponseStatus.ERROR, data, msg);
    }
    public static <T> WeatherResource<T> loading(@Nullable T data) {
        return new WeatherResource<>(ResponseStatus.LOADING, data, null);
    }
    public static <T> WeatherResource<T> failed() {
        return new WeatherResource<>(ResponseStatus.FAILED, null, null);
    }
    public enum ResponseStatus {SUCCESSFUL, ERROR, LOADING, FAILED}

}