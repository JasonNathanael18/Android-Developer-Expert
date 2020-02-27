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

    /**
     * SEARCH MOVIE
     */
    @GET(Constants.ApiEndPoint.SEARCH_MOVIE)
    fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("language") city: String,
        @Query("query") query: String): Call<ShowsResponse>

    /**
     * SEARCH TV
     */
    @GET(Constants.ApiEndPoint.SEARCH_TV)
    fun searchTv(
        @Query("api_key") apiKey: String,
        @Query("language") city: String,
        @Query("query") query: String): Call<ShowsResponse>

    /**
     *
     */
    @GET(Constants.ApiEndPoint.NEW_RELEASE)
    fun newRelease(
        @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") gteDate: String,
        @Query("primary_release_date.lte") lteDate: String): Call<ShowsResponse>
}