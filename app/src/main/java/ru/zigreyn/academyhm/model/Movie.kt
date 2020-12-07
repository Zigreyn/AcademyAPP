package ru.zigreyn.academyhm.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val movieName: String?,
    val genre: String,
    val rating: Int,
    val reviewsCount: Int,
    val description: String,
    val duration: Int,
    var isLiked: Boolean,
    val minAge: String,
    @DrawableRes var posterId: Int,
    val cast: List<Actor>

) : Parcelable