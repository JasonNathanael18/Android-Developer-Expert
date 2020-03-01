package com.jason.favouriteapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowsDetail(
    val id: Int? = null,
    val showTitle: String? = null,
    val showDesc: String? = null,
    val showVote: Float? = null,
    val showPoster: String? = null,
    val showReleaseDate: String? = null
) : Parcelable