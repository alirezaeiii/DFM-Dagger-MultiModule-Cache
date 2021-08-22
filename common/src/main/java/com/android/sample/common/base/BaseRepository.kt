package com.android.sample.common.base

import com.android.sample.common.util.NoDataException
import io.reactivex.Observable

abstract class BaseRepository<T> {

    private var cacheIsDirty = false

    protected abstract fun getResultFromRemoteDataSource(mediaId: String?): Observable<T>

    protected abstract val resultFromLocalDataSource: T?

    private fun getRemoteResult(mediaId: String?) = getResultFromRemoteDataSource(mediaId)
        .doOnComplete { cacheIsDirty = false }

    fun getResult(mediaId: String?): Observable<T> =
        Observable.fromCallable { cacheIsDirty }.flatMap {
            if (it) {
                getRemoteResult(mediaId)
            } else {
                val resultFromLocalDataSource = resultFromLocalDataSource
                Observable.create { subscriber ->
                    if (resultFromLocalDataSource == null) {
                        subscriber.onError(NoDataException())
                    } else {
                        subscriber.onNext(resultFromLocalDataSource)
                    }
                }
            }.onErrorResumeNext(getRemoteResult(mediaId))
        }

    fun refresh() {
        cacheIsDirty = true
    }
}