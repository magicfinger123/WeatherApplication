package ng.com.cwg.weatherapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity
import ng.com.cwg.weatherapplication.db.io.SaveWeatherAppDb

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private var savedEntityLiveData = MutableLiveData<MutableList<SaveWeatherInfoEntity>>()
    private val saveWeatherAppDb = Room.databaseBuilder(getApplication(), SaveWeatherAppDb::class.java,"savedWeatherInfo").allowMainThreadQueries().fallbackToDestructiveMigration().build()

    fun observeFavorites():LiveData<MutableList<SaveWeatherInfoEntity>>{
        return savedEntityLiveData
    }
    fun getFavorites(){
        val favorites = saveWeatherAppDb.favoritesDao.favorites
        savedEntityLiveData.value = favorites

    }
}
