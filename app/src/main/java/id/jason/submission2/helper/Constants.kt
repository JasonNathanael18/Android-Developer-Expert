package id.jason.submission2.helper

import id.jason.submission2.BuildConfig

class Constants {
    object ApiEndPoint {
        const val BASE_URL = BuildConfig.BASE_URL
        const val MOVIE = "discover/movie"
        const val TV = "discover/tv"
        const val SEARCH_MOVIE = "search/movie"
        const val SEARCH_TV =  "search/tv"
    }
    object API{
        const val API_KEY = BuildConfig.API_KEY
    }
    object State{
        const val HOME_DETAIL_DATA ="home_detail_data"
    }
    object IntentBundle{
        const val SEARCH_QUERY ="search_query"
        const val SEARCH_CATEGORY="search_category"
    }
}