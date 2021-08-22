package com.android.sample.core.response

import android.os.Parcelable
import com.android.sample.core.database.DbMedia
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(
    val id: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "thumbnail_url")
    val thumbnailUrl: String
) : Parcelable

fun List<Media>.asDatabaseModel(): List<DbMedia> = map { DbMedia(
        id = it.id,
        createdAt = it.createdAt,
        thumbnailUrl = it.thumbnailUrl) }