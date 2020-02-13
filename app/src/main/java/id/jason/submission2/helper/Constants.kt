package id.jason.submission2.helper

import id.jason.submission2.BuildConfig

class Constants {
    object ApiEndPoint {
        const val BASE_URL = BuildConfig.BASE_URL
        const val MOVIE = "movie"
        const val TV = "tv"
    }
    object API{
        const val API_KEY = BuildConfig.API_KEY
    }
    object State{
        const val HOME_DETAIL_DATA ="home_detail_data"
    }
}