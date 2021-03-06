package com.android.sample.core.network

import com.android.sample.core.response.Media
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("shared/{sharedID}/media")
    fun getMedia(
        @Path("sharedID") sharedID: String,
        @Query("m") resizeMode: String,
        @Query("w") width: Int,
        @Query("h") height: Int
    ): Observable<List<Media>>
}