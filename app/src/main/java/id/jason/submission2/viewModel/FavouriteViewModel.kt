package id.jason.submission2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.jason.submission2.database.ShowDatabase
import id.jason.submission2.model.ShowsDetail
import id.jason.submission2.model.repository.ShowRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val showRepository: ShowRepository
    // LiveData gives us updated words when they change.
    val allMovieShow: LiveData<List<ShowsDetail>>
    val allTvShow: LiveData<List<ShowsDetail>>

    init {
        val showDao = ShowDatabase.getDatabase(application).showDao()
        showRepository = ShowRepository(showDao)
        allMovieShow = showRepository.allFavouriteMovieList
        allTvShow = showRepository.allFavouriteTvList
    }

    fun insert(show: ShowsDetail) = viewModelScope.launch {
        showRepository.insert(show)
    }

    fun deleteShowById(showId: Int) = viewModelScope.launch {
        showRepository.deleteFavouriteItem(showId)
    }
}
