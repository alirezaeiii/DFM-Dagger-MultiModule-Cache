package com.android.sample.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(medias: List<DbMedia>): Completable

    @Query("SELECT * FROM media")
    fun getMedia(): List<DbMedia>?
}