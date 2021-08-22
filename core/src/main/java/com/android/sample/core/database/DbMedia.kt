package com.android.sample.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.sample.core.response.Media

@Entity(tableName = "media")
class DbMedia(
    @PrimaryKey val id: String,
    val createdAt: String,
    val thumbnailUrl: String
)

fun List<DbMedia>.asDomainModel(): List<Media> = map { Media(
        id = it.id,
        createdAt = it.createdAt,
        thumbnailUrl = it.thumbnailUrl) }
