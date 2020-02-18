package id.jason.submission2.model.repository

import androidx.lifecycle.LiveData
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.model.dao.ShowDao

class ShowRepository (private val showDao: ShowDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allFavouriteMovieList: LiveData<List<ShowsDetail>> = showDao.getFavouriteMovieList()

    val allFavouriteTvList: LiveData<List<ShowsDetail>> = showDao.getFavouriteTvList()

    suspend fun insert(shows: ShowsDetail) {
        showDao.insert(shows)
    }

    suspend fun deleteFavouriteItem(showId: Int){
        showDao.deleteFavouriteItem(showId)
    }
}