package id.jason.submission2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.model.dao.FavouriteDao
import id.jason.submission2.model.dao.ShowDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ShowsDetail::class], version = 1, exportSchema = false)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun showDao(): ShowDao
    abstract fun showFavourite(): FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: ShowDatabase? = null

        fun getDatabase(context: Context): ShowDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class ShowDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }
    }
}