package com.android.sample.core.repository

import com.android.sample.common.base.BaseListRepository
import com.android.sample.common.util.Constants.CROP
import com.android.sample.common.util.Constants.MINIMUM_SIZE
import com.android.sample.common.util.Constants.SHARED_ID
import com.android.sample.core.database.MediaDao
import com.android.sample.core.database.asDomainModel
import com.android.sample.core.network.ApiService
import com.android.sample.core.response.Media
import com.android.sample.core.response.asDatabaseModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(
    private val remoteDataSource: ApiService,
    private val dao: MediaDao
) : BaseListRepository<Media>() {

    override fun getResultFromRemoteDataSource(mediaId: String?): Observable<List<Media>> =
        remoteDataSource.getMedia(SHARED_ID, "$CROP,$MINIMUM_SIZE").flatMap {
            dao.insert(it.asDatabaseModel()).andThen(Observable.fromCallable { it })
        }

    override val resultFromLocalDataSource: List<Media>? =
        dao.getMedia()?.asDomainModel()
}