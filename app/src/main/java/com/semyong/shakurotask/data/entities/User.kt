package com.semyong.shakurotask.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @field:Json(name = "login")
    val login: String? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "avatar_url")
    val avatar_url: String? = null,
) : Parcelable
