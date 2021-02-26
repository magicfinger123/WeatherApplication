package ng.com.cwg.weatherapplication.db.entity;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class SavedWeatherDao_Impl implements SavedWeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSaveWeatherInfoEntity;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfSaveWeatherInfoEntity;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfSaveWeatherInfoEntity;

  private final SharedSQLiteStatement __preparedStmtOfNukeTable;

  public SavedWeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSaveWeatherInfoEntity = new EntityInsertionAdapter<SaveWeatherInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `savedWeatherInfo`(`id`,`latitude`,`longitude`,`weather_type`,`average_temperature`,`date`,`title`,`address`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SaveWeatherInfoEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getLatitude() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLatitude());
        }
        if (value.getLongitude() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLongitude());
        }
        if (value.getWeatherType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getWeatherType());
        }
        if (value.getAverageTemperature() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAverageTemperature());
        }
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTitle());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAddress());
        }
      }
    };
    this.__deletionAdapterOfSaveWeatherInfoEntity = new EntityDeletionOrUpdateAdapter<SaveWeatherInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `savedWeatherInfo` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SaveWeatherInfoEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfSaveWeatherInfoEntity = new EntityDeletionOrUpdateAdapter<SaveWeatherInfoEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `savedWeatherInfo` SET `id` = ?,`latitude` = ?,`longitude` = ?,`weather_type` = ?,`average_temperature` = ?,`date` = ?,`title` = ?,`address` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SaveWeatherInfoEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getLatitude() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLatitude());
        }
        if (value.getLongitude() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLongitude());
        }
        if (value.getWeatherType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getWeatherType());
        }
        if (value.getAverageTemperature() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAverageTemperature());
        }
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTitle());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAddress());
        }
        stmt.bindLong(9, value.getId());
      }
    };
    this.__preparedStmtOfNukeTable = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM savedWeatherInfo";
        return _query;
      }
    };
  }

  @Override
  public long addToFavorites(SaveWeatherInfoEntity weatherInfoEntity) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfSaveWeatherInfoEntity.insertAndReturnId(weatherInfoEntity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteFavorites(SaveWeatherInfoEntity cartEntity) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfSaveWeatherInfoEntity.handle(cartEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFavorite(SaveWeatherInfoEntity weatherInfoEntity) {
    __db.beginTransaction();
    try {
      __updateAdapterOfSaveWeatherInfoEntity.handle(weatherInfoEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void nukeTable() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfNukeTable.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfNukeTable.release(_stmt);
    }
  }

  @Override
  public List<SaveWeatherInfoEntity> getFavorites() {
    final String _sql = "select * from savedWeatherInfo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
      final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
      final int _cursorIndexOfWeatherType = _cursor.getColumnIndexOrThrow("weather_type");
      final int _cursorIndexOfAverageTemperature = _cursor.getColumnIndexOrThrow("average_temperature");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfAddress = _cursor.getColumnIndexOrThrow("address");
      final List<SaveWeatherInfoEntity> _result = new ArrayList<SaveWeatherInfoEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SaveWeatherInfoEntity _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpLatitude;
        _tmpLatitude = _cursor.getString(_cursorIndexOfLatitude);
        final String _tmpLongitude;
        _tmpLongitude = _cursor.getString(_cursorIndexOfLongitude);
        final String _tmpWeatherType;
        _tmpWeatherType = _cursor.getString(_cursorIndexOfWeatherType);
        final String _tmpAverageTemperature;
        _tmpAverageTemperature = _cursor.getString(_cursorIndexOfAverageTemperature);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        _item = new SaveWeatherInfoEntity(_tmpId,_tmpLatitude,_tmpLongitude,_tmpWeatherType,_tmpAverageTemperature,_tmpDate,_tmpTitle,_tmpAddress);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
