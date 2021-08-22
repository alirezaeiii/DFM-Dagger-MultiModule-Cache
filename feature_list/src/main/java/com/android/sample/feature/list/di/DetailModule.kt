package com.android.sample.feature.list.di

import androidx.navigation.fragment.navArgs
import com.android.sample.core.response.Media
import com.android.sample.feature.list.ui.DetailFragment
import com.android.sample.feature.list.ui.DetailFragmentArgs
import dagger.Module
import dagger.Provides

@Module
class DetailModule(private val fragment: DetailFragment) {

    @Provides
    internal fun provideMedia(): Media {
        val args: DetailFragmentArgs by fragment.navArgs()
        return args.media
    }
}