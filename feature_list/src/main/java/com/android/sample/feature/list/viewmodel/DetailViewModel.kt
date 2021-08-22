package com.android.sample.feature.list.viewmodel

import com.android.sample.common.base.BaseRepository
import com.android.sample.common.base.BaseViewModel
import com.android.sample.common.util.schedulers.BaseSchedulerProvider
import com.android.sample.core.response.Media

class DetailViewModel(
    repository: BaseRepository<Media>,
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel<Media>(repository, schedulerProvider) {
}