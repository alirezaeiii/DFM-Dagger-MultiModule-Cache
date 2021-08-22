package com.android.sample.common.base

import com.android.sample.common.util.schedulers.BaseSchedulerProvider

open class BaseListViewModel<T>(
    private val repository: BaseListRepository<T>,
    private val schedulerProvider: BaseSchedulerProvider
) : BaseViewModel<List<T>>(repository, schedulerProvider)