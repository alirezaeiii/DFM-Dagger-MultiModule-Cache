package com.android.sample.core.database

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

object DbMediaConverter {

    private lateinit var dbLinkJsonAdapter: JsonAdapter<List<DbMedia>>

    fun initialize(moshi: Moshi){
        val listDbLink = Types.newParameterizedType(MutableList::class.java, DbMedia::class.java)
        dbLinkJsonAdapter = moshi.adapter(listDbLink)
    }

    @TypeConverter
    @JvmStatic
    fun jsonToList(value: String): List<DbMedia>? = dbLinkJsonAdapter.fromJson(value)

    @TypeConverter
    @JvmStatic
    fun listToJson(list: List<DbMedia>?): String = dbLinkJsonAdapter.toJson(list)
}