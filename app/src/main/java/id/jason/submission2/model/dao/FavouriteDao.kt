package id.jason.submission2.model.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.jason.submission2.model.ShowsDetail

@Dao
interface FavouriteDao {
    @Query("SELECT * from show_table ORDER BY id DESC")
    fun getAllFavouriteList(): List<ShowsDetail>

    @Query("SELECT * from show_table WHERE showTitle IS NOT NULL ORDER BY id DESC")
    fun getFavouriteMovie(): Cursor

    @Query("SELECT * from show_table WHERE showTitle AND id = :movieId IS NOT NULL ORDER BY id DESC")
    fun queryById(movieId: Int): Cursor
}