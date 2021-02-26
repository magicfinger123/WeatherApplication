package ng.com.cwg.weatherapplication.db.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author .: Ossai Michael
 * @email ..: mikeossaiofficial@gmail.com, michael.ossai@cwg-plc.com
 * @created : 11/09/2020 11:45
 */
@Dao
public interface SavedWeatherDao {
    @Insert
    long addToFavorites(SaveWeatherInfoEntity weatherInfoEntity);

    @Update
    void updateFavorite(SaveWeatherInfoEntity weatherInfoEntity);

    @Delete
    void deleteFavorites(SaveWeatherInfoEntity cartEntity);

    @Query("select * from savedWeatherInfo")
    List<SaveWeatherInfoEntity> getFavorites();

    @Query("DELETE FROM savedWeatherInfo")
    void nukeTable();

}
