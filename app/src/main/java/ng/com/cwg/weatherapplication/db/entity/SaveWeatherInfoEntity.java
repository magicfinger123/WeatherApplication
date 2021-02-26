package ng.com.cwg.weatherapplication.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 25/10/2020 21:07
 */
@Entity(tableName = "savedWeatherInfo")
public class SaveWeatherInfoEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "weather_type")
    private String weatherType;

    @ColumnInfo(name = "average_temperature")
    private String averageTemperature;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "address")
    private String address;

    public SaveWeatherInfoEntity(long id, String latitude, String longitude, String weatherType, String averageTemperature, String date, String title, String address) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weatherType = weatherType;
        this.averageTemperature = averageTemperature;
        this.date = date;
        this.title = title;
        this.address = address;
    }

    @Ignore
    public SaveWeatherInfoEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(String averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
