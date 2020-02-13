package id.jason.submission2.connection

import id.jason.submission2.helper.Constants
import id.jason.submission2.model.ShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APICollections {
    /**
     * Movie
     */
    @GET(Constants.ApiEndPoint.MOVIE)
    fun movie(@Query("api_key") apiKey: String,
             @Query("language") city: String): Call<ShowsResponse>

    /**
     * TV
     */
    @GET(Constants.ApiEndPoint.TV)
    fun tv(@Query("api_key") apiKey: String,
             @Query("language") city: String): Call<ShowsResponse>
}