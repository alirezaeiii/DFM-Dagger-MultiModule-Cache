package com.android.sample.feature.list.di

import com.android.sample.core.di.CoreComponent
import com.android.sample.core.di.FeatureScope
import com.android.sample.feature.list.ui.MainFragment
import dagger.Component

@FeatureScope
@Component(modules = [MediaModule::class],
    dependencies = [CoreComponent::class]
)
interface MediaComponent {

    fun inject(mainFragment: MainFragment)
}