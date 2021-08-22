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

fun List<Media>.asDatabaseModel(): List<DbMedia> {
    val dbMedias = ArrayList<DbMedia>()
    this.forEach { media ->
        dbMedias.add(
            DbMedia(
                id = media.id,
                createdAt = media.createdAt,
                thumbnailUrl = media.thumbnailUrl
            )
        )
    }
    return dbMedias
}