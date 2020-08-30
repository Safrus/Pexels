package kz.tinker.pexel.data.api

import androidx.annotation.WorkerThread
import kz.tinker.pexel.data.model.CuratedPhotos
import kz.tinker.pexel.data.model.SearchedPhotos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelApi {

    @WorkerThread
    @GET("v1/curated")
    suspend fun getCuratedPhotos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<CuratedPhotos>

    @WorkerThread
    @GET("v1/search")
    suspend fun getSearchedPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): Response<SearchedPhotos>
}