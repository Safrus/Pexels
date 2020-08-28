package kz.tinker.pexel.data.repository

import kotlinx.coroutines.flow.Flow

interface CuratedPhotosRepository {
    fun getCuratedPhotos(perPage: Int, page: Int): Flow<Any?>
    suspend fun getCuratedPhotosFromAPI(perPage: Int, page: Int): Any?
}