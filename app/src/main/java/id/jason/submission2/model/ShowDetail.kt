package id.jason.submission2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "show_table")
data class ShowsDetail(
    @PrimaryKey @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val showName: String? = null,
    @SerializedName("original_title") val showTitle: String? = null,
    @SerializedName("overview") val showDesc: String? = null,
    @SerializedName("vote_average") val showVote: Float? = null,
    @SerializedName("poster_path") val showPoster: String? = null,
    @SerializedName("release_date") val showReleaseDate: String? = null,
    @SerializedName("first_air_date") val showFirstAirDate: String? = null
) : Parcelable