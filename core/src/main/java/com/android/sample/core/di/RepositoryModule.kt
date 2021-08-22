package com.android.sample.core.di

import com.android.sample.common.base.BaseListRepository
import com.android.sample.core.repository.MediaRepository
import com.android.sample.core.response.Media
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindMediaRepository(
        mediaRepository: MediaRepository
    ): BaseListRepository<Media>
}