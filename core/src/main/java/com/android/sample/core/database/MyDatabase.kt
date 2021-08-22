package com.android.sample.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.sample.core.BuildConfig

@Database(
    entities = [DbMedia::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = BuildConfig.DATABASE_EXPORT_SCHEMA
)
@TypeConverters(DbMediaConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun mediaDao(): MediaDao
}