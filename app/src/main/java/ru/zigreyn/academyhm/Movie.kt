package ru.zigreyn.academyhm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(var movieName: String?, var isLiked:Boolean) : Parcelable