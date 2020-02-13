package id.jason.submission2.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.jason.submission2.connection.RetrofitService
import id.jason.submission2.helper.Constants
import id.jason.submission2.model.ShowsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvViewModel: ViewModel() {
    private var data = MutableLiveData<ShowsResponse>()

    internal fun setData(language: String) {
        RetrofitService().api().tv(Constants.API.API_KEY, language)
            .enqueue(object : Callback<ShowsResponse> {
                override fun onFailure(call: Call<ShowsResponse>, t: Throwable) {
                    data.postValue(null)
                }

                override fun onResponse(
                    call: Call<ShowsResponse>,
                    response: Response<ShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        //data.value = response.body()
                        data.postValue(response.body())
                    }
                }
            })
    }

    internal fun getData(): MutableLiveData<ShowsResponse> {
        return data
    }
}