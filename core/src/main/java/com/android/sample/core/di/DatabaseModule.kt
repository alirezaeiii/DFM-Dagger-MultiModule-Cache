package com.android.sample.core.di

import android.content.Context
import androidx.room.Room
import com.android.sample.core.BuildConfig
import com.android.sample.core.database.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMyDatabase(context: Context): MyDatabase =
        Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideMediaDao(db: MyDatabase) = db.mediaDao()
}