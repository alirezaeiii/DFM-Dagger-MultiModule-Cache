package com.android.sample.feature.list.di

import com.android.sample.core.di.CoreComponent
import com.android.sample.core.di.FeatureScope
import com.android.sample.feature.list.ui.DetailFragment
import dagger.Component

@FeatureScope
@Component(modules = [DetailModule::class],
    dependencies = [CoreComponent::class]
)
interface DetailComponent {

    fun inject(detailFragment: DetailFragment)
}