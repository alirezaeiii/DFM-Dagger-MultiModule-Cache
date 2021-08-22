package com.android.sample.common.base

abstract class BaseListRepository<T> : BaseRepository<List<T>>() {

    override fun isEmpty(resultFromLocalDataSource : List<T>?) =
        resultFromLocalDataSource?.isEmpty()!!
}