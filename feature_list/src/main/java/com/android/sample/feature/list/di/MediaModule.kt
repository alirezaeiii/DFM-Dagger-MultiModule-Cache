package com.android.sample.feature.list.di

import com.android.sample.common.base.BaseListRepository
import com.android.sample.common.extension.viewModel
import com.android.sample.common.util.schedulers.BaseSchedulerProvider
import com.android.sample.core.response.Media
import com.android.sample.feature.list.ui.MainFragment
import com.android.sample.feature.list.viewmodel.MediaViewModel
import dagger.Module
import dagger.Provides

@Module
class MediaModule(private val fragment: MainFragment) {

    @Provides
    fun providesMediaViewModel(
        repository: BaseListRepository<Media>,
        schedulerProvider: BaseSchedulerProvider
    ) = fragment.viewModel {
        MediaViewModel(repository, schedulerProvider)
    }
}