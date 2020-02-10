package id.jason.submission2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shows (
    var showTitle: String,
    var showDate: String,
    var showDesc: String,
    var showRating: String,
    var showPhoto: Int
):Parcelable