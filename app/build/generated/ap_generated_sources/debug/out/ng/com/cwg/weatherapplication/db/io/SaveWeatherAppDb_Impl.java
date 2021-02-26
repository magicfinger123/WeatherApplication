package ng.com.cwg.weatherapplication.db.io;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import ng.com.cwg.weatherapplication.db.entity.SavedWeatherDao;
import ng.com.cwg.weatherapplication.db.entity.SavedWeatherDao_Impl;

@SuppressWarnings("unchecked")
public final class SaveWeatherAppDb_Impl extends SaveWeatherAppDb {
  private volatile SavedWeatherDao _savedWeatherDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `savedWeatherInfo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `latitude` TEXT, `longitude` TEXT, `weather_type` TEXT, `average_temperature` TEXT, `date` TEXT, `title` TEXT, `address` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f33a457bec3e56bff790755357f6f519\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `savedWeatherInfo`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSavedWeatherInfo = new HashMap<String, TableInfo.Column>(8);
        _columnsSavedWeatherInfo.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsSavedWeatherInfo.put("latitude", new TableInfo.Column("latitude", "TEXT", false, 0));
        _columnsSavedWeatherInfo.put("longitude", new TableInfo.Column("longitude", "TEXT", false, 0));
        _columnsSavedWeatherInfo.put("weather_type", new TableInfo.Column("weather_type", "TEXT", false, 0));
        _columnsSavedWeatherInfo.put("average_temperature", new TableInfo.Column("average_temperature", "TEXT", false, 0));
        _columnsSavedWeatherInfo.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsSavedWeatherInfo.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsSavedWeatherInfo.put("address", new TableInfo.Column("address", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSavedWeatherInfo = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSavedWeatherInfo = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSavedWeatherInfo = new TableInfo("savedWeatherInfo", _columnsSavedWeatherInfo, _foreignKeysSavedWeatherInfo, _indicesSavedWeatherInfo);
        final TableInfo _existingSavedWeatherInfo = TableInfo.read(_db, "savedWeatherInfo");
        if (! _infoSavedWeatherInfo.equals(_existingSavedWeatherInfo)) {
          throw new IllegalStateException("Migration didn't properly handle savedWeatherInfo(ng.com.cwg.weatherapplication.db.entity.SaveWeatherInfoEntity).\n"
                  + " Expected:\n" + _infoSavedWeatherInfo + "\n"
                  + " Found:\n" + _existingSavedWeatherInfo);
        }
      }
    }, "f33a457bec3e56bff790755357f6f519", "5e441b337caceae82fd35625d9d7c218");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "savedWeatherInfo");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `savedWeatherInfo`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public SavedWeatherDao getFavoritesDao() {
    if (_savedWeatherDao != null) {
      return _savedWeatherDao;
    } else {
      synchronized(this) {
        if(_savedWeatherDao == null) {
          _savedWeatherDao = new SavedWeatherDao_Impl(this);
        }
        return _savedWeatherDao;
      }
    }
  }
}
