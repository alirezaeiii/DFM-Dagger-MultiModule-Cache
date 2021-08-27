package com.android.sample.common.base

import com.android.sample.common.util.NoDataException
import io.reactivex.Observable

abstract class BaseRepository<T> {

    private var cacheIsDirty = false

    protected abstract val resultFromRemoteDataSource: Observable<T>

    protected abstract val resultFromLocalDataSource: T?

    private val remoteResult
        get() = resultFromRemoteDataSource.doOnComplete { cacheIsDirty = false }

    open fun isEmpty(resultFromLocalDataSource: T?) =
        resultFromLocalDataSource == null

    val result: Observable<T> =
        Observable.fromCallable { cacheIsDirty }.flatMap {
            if (it) {
                remoteResult
            } else {
                val resultFromLocalDataSource = resultFromLocalDataSource
                Observable.create { subscriber ->
                    if (isEmpty(resultFromLocalDataSource)) {
                        subscriber.onError(NoDataException())
                    } else {
                        resultFromLocalDataSource?.let { it1 -> subscriber.onNext(it1) }
                    }
                }
            }.onErrorResumeNext(remoteResult)
        }

    fun refresh() {
        cacheIsDirty = true
    }
}