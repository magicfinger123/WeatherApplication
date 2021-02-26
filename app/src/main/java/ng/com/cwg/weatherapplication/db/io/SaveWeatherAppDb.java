package ng.com.cwg.weatherapplication.db.io;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity;
import ng.com.cwg.weatherapplication.db.entity.SavedWeatherDao;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 11/09/2020 13:01
 */

@Database(entities = {SaveWeatherInfoEntity.class},version = 2)
public abstract class SaveWeatherAppDb extends RoomDatabase {
    public abstract SavedWeatherDao getFavoritesDao();
}

