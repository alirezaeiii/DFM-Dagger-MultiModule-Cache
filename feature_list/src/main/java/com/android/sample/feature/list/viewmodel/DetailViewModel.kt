package com.android.sample.feature.list.viewmodel

import com.android.sample.common.base.BaseRepository
import com.android.sample.common.base.BaseViewModel
import com.android.sample.common.util.schedulers.BaseSchedulerProvider
import com.android.sample.core.response.Media
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    repository: BaseRepository<Media>,
    schedulerProvider: BaseSchedulerProvider,
    media: Media
) : BaseViewModel<Media>(repository, schedulerProvider, media.id)