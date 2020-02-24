package id.jason.submission2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.jason.submission2.connection.RetrofitService
import id.jason.submission2.model.ShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import id.jason.submission2.helper.Constants.API.API_KEY

class ShowSearchViewModel: ViewModel() {
    private var searchShowData = MutableLiveData<ShowsResponse>()

    internal fun setDataSearchMovie(
        searchQuery: String,
        language: String
    ) {
        RetrofitService().api().searchMovie(API_KEY,language,searchQuery)
            .enqueue(object : Callback<ShowsResponse> {
                override fun onFailure(call: Call<ShowsResponse>, t: Throwable) {
                    searchShowData.postValue(null)
                }

                override fun onResponse(
                    call: Call<ShowsResponse>,
                    response: Response<ShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        searchShowData.postValue(response.body())
                    }
                }
            })
    }

    internal fun setDataSearchTvShow(
        searchQuery: String,
        language: String
    ) {
        RetrofitService().api().searchTv(API_KEY,language,searchQuery)
            .enqueue(object : Callback<ShowsResponse> {
                override fun onFailure(call: Call<ShowsResponse>, t: Throwable) {
                    searchShowData.postValue(null)
                }

                override fun onResponse(
                    call: Call<ShowsResponse>,
                    response: Response<ShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        searchShowData.postValue(response.body())
                    }
                }
            })
    }

    internal fun getDataSearchEvent(): MutableLiveData<ShowsResponse> {
        return searchShowData
    }
}