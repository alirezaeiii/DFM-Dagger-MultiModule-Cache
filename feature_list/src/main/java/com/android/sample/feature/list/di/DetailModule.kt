package com.android.sample.feature.list.di

import androidx.navigation.fragment.navArgs
import com.android.sample.common.base.BaseRepository
import com.android.sample.common.extension.viewModel
import com.android.sample.common.util.schedulers.BaseSchedulerProvider
import com.android.sample.core.response.Media
import com.android.sample.feature.list.ui.DetailFragment
import com.android.sample.feature.list.ui.DetailFragmentArgs
import com.android.sample.feature.list.viewmodel.DetailViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailModule(private val fragment: DetailFragment) {

    @Provides
    fun provideSectionViewModel(
        repository: BaseRepository<Media>,
        schedulerProvider: BaseSchedulerProvider,
    ) = fragment.viewModel {
        DetailViewModel(repository, schedulerProvider)
    }

    @Provides
    internal fun provideLink(): Media {
        val args: DetailFragmentArgs by fragment.navArgs()
        return args.link
    }
}