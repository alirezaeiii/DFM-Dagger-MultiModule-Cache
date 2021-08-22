package com.android.sample.feature.list.viewmodel

import com.android.sample.common.base.BaseListRepository
import com.android.sample.common.base.BaseListViewModel
import com.android.sample.common.util.schedulers.BaseSchedulerProvider
import com.android.sample.core.response.Media
import javax.inject.Inject

class MediaViewModel @Inject constructor(
    repository: BaseListRepository<Media>,
    schedulerProvider: BaseSchedulerProvider,
) : BaseListViewModel<Media>(repository, schedulerProvider)