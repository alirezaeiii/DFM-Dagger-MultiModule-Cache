package com.android.sample.common.base

import com.android.sample.common.util.schedulers.BaseSchedulerProvider

open class BaseListViewModel<T>(
    repository: BaseListRepository<T>,
    schedulerProvider: BaseSchedulerProvider,
    mediaId: String?
) : BaseViewModel<List<T>>(repository, schedulerProvider, mediaId)