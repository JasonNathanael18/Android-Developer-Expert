package id.jason.submission2.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.jason.submission2.model.ShowsDetail

@Dao
interface ShowDao {

    @Query("SELECT * from show_table WHERE showTitle IS NOT NULL ORDER BY id DESC")
    fun getFavouriteMovieList(): LiveData<List<ShowsDetail>>

    @Query("SELECT * from show_table WHERE showName IS NOT NULL ORDER BY id DESC")
    fun getFavouriteTvList(): LiveData<List<ShowsDetail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(show: ShowsDetail)

    @Query("DELETE FROM show_table")
    suspend fun deleteFavouriteAll()

    @Query("DELETE FROM show_table WHERE id = :showId")
    suspend fun deleteFavouriteItem(showId: Int)
}