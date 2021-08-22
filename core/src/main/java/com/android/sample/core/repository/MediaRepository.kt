package com.android.sample.core.repository

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.android.sample.common.base.BaseListRepository
import com.android.sample.common.util.Constants.BOUNDING_BOX
import com.android.sample.common.util.Constants.CROP
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
    context: Context,
    private val remoteDataSource: ApiService,
    private val dao: MediaDao
) : BaseListRepository<Media>() {

    private val width: Int
    private val height: Int

    init {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        width = metrics.widthPixels
        height = metrics.heightPixels
    }

    override fun getResultFromRemoteDataSource(): Observable<List<Media>> =
        remoteDataSource.getMedia(SHARED_ID, "$CROP,$BOUNDING_BOX", width, height).flatMap {
            dao.insert(it.asDatabaseModel()).andThen(Observable.fromCallable { it })
        }

    override fun getResultFromLocalDataSource(): List<Media>? =
        dao.getMedia()?.asDomainModel()
}