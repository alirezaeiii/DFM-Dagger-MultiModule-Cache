package com.android.sample.common.base

import io.reactivex.Observable

abstract class BaseListRepository<T> : BaseRepository<List<T>>() {

    abstract override fun getResultFromRemoteDataSource(mediaId: String?): Observable<List<T>>

    abstract override val resultFromLocalDataSource: List<T>?
}