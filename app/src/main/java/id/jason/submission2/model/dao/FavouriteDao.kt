package id.jason.submission2.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import id.jason.submission2.model.ShowsDetail

@Dao
interface FavouriteDao {
    @Query("SELECT * from show_table ORDER BY id DESC")
    fun getAllFavouriteList(): List<ShowsDetail>
}