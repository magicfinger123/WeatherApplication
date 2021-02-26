package ng.com.cwg.weatherapplication.model.weather;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 25/02/2021 00:26
 */
public class DayAndTemperature {
    private String day;
    private String temp;
    private String weatherType;

    public DayAndTemperature(String day, String temp, String weatherType) {
        this.day = day;
        this.temp = temp;
        this.weatherType = weatherType;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
