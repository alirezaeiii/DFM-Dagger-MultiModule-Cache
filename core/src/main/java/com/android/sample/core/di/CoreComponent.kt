package com.android.sample.core.di

import android.content.Context
import com.android.sample.common.base.BaseListRepository
import com.android.sample.common.util.schedulers.BaseSchedulerProvider
import com.android.sample.core.database.MediaDao
import com.android.sample.core.network.ApiService
import com.android.sample.core.response.Media
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        UtilsModule::class,
        RepositoryModule::class]
)
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph apiService
     *
     * @return ApiService
     */
    fun apiService(): ApiService

    /**
     * Provide dependency graph MediaDao
     *
     * @return MediaDao
     */
    fun mediaDao(): MediaDao

    /**
     * Provide dependency graph SchedulerProvider
     *
     * @return BaseSchedulerProvider
     */
    fun schedulerProvider(): BaseSchedulerProvider

    /**
     * Provide dependency graph MediaRepository
     *
     * @return BaseListRepository
     */
    fun mediaRepository(): BaseListRepository<Media>
}