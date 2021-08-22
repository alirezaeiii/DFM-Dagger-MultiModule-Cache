package com.android.sample.common.base

import com.android.sample.common.util.NoDataException
import io.reactivex.Observable

abstract class BaseRepository<T> {

    private var cacheIsDirty = false

    protected abstract fun getResultFromRemoteDataSource(): Observable<T>

    protected abstract fun getResultFromLocalDataSource(): T?

    private fun getRemoteResult() = getResultFromRemoteDataSource()
        .doOnComplete { cacheIsDirty = false }

    open fun isEmpty( resultFromLocalDataSource: T?) =
        resultFromLocalDataSource == null

    fun getResult(): Observable<T> =
        Observable.fromCallable { cacheIsDirty }.flatMap {
            if (it) {
                getRemoteResult()
            } else {
                val resultFromLocalDataSource = getResultFromLocalDataSource()
                Observable.create { subscriber ->
                    if (isEmpty(resultFromLocalDataSource)) {
                        subscriber.onError(NoDataException())
                    } else {
                        resultFromLocalDataSource?.let { it1 -> subscriber.onNext(it1) }
                    }
                }
            }.onErrorResumeNext(getRemoteResult())
        }

    fun refresh() {
        cacheIsDirty = true
    }
}