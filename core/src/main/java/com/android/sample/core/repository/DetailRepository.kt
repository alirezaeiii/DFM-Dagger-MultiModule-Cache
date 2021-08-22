package com.android.sample.core.repository

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.android.sample.common.base.BaseRepository
import com.android.sample.common.util.Constants.BOUNDING_BOX
import com.android.sample.core.network.ApiService
import com.android.sample.core.response.Media
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val remoteDataSource: ApiService, override val resultFromLocalDataSource: Media?
) : BaseRepository<Media>() {

//    private val width: Int
//    private val height: Int
//
//    init {
//        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val metrics = DisplayMetrics()
//        wm.defaultDisplay.getMetrics(metrics)
//        width = metrics.widthPixels
//        height = metrics.heightPixels
//    }

    override fun getResultFromRemoteDataSource(mediaId: String?): Observable<Media> =
         remoteDataSource.getMedia(mediaId!!, 100, 100, BOUNDING_BOX)
}