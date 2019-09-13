package com.qiubo.mediamonks.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(val id: Int, val userId: Int, val title: String) : Parcelable
data class Photo(val id: Int, val title: String, val url: String, val thumbnailUrl: String)