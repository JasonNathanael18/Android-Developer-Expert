package id.jason.submission2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowsDetail(
    @SerializedName("name") val showName: String = "",
    @SerializedName("original_title") val showTitle: String= "",
    @SerializedName("overview") val showDesc: String= "",
    @SerializedName("vote_average") val showVote: Float=0.0f,
    @SerializedName("poster_path") val showPoster: String= "",
    @SerializedName("release_date") val showReleaseDate: String= "",
    @SerializedName("first_air_date") val showFirstAirDate: String= ""
) : Parcelable