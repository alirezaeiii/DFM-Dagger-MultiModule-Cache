package com.android.sample.common.base

import io.reactivex.Observable

abstract class BaseListRepository<T> : BaseRepository<List<T>>() {

    abstract override fun getResultFromRemoteDataSource(): Observable<List<T>>

    abstract override fun getResultFromLocalDataSource(): List<T>?

    override fun isEmpty(resultFromLocalDataSource : List<T>?) =
        resultFromLocalDataSource?.isEmpty()!!
}