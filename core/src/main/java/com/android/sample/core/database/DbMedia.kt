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

fun List<DbMedia>.asDomainModel(): List<Media> {
    val medias = ArrayList<Media>()
    this.forEach { media ->
        medias.add(
            Media(
                id = media.id,
                createdAt = media.createdAt,
                thumbnailUrl = media.thumbnailUrl
            )
        )
    }
    return medias
}
