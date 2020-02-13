package id.jason.submission2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowsDetail(
    @SerializedName("name") val showName: String? = null,
    @SerializedName("original_title") val showTitle: String? = null,
    @SerializedName("overview") val showDesc: String? = null,
    @SerializedName("vote_average") val showVote: Float? = null,
    @SerializedName("poster_path") val showPoster: String? = null,
    @SerializedName("release_date") val showReleaseDate: String? = null,
    @SerializedName("first_air_date") val showFirstAirDate: String? = null
) : Parcelable