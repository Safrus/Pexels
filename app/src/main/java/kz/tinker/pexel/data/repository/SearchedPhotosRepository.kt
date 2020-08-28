package kz.tinker.pexel.data.repository

import kotlinx.coroutines.flow.Flow

interface SearchedPhotosRepository {
    fun getSearchedPhotos(query: String, perPage: Int): Flow<Any?>
    suspend fun getSearchedPhotosFromAPI(query: String, perPage: Int): Any?
}