package kz.tinker.pexel.data.api

import androidx.annotation.WorkerThread
import kz.tinker.pexel.data.model.CuratedPhotos
import kz.tinker.pexel.data.model.Photo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelApi {

    @GET("v1/photos/{id}")
    fun getPhoto(@Path("id") id: Int): Call<Photo>

    @WorkerThread
    @GET("v1/curated")
    suspend fun getCuratedPhotos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<CuratedPhotos>
}