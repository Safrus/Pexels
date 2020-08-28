package kz.tinker.pexel.data.repository

import kotlinx.coroutines.flow.flow
import kz.tinker.pexel.data.api.PexelApi

class SearchedPhotosRepository(private val api: PexelApi) {

    fun getSearchedPhotos(query: String, perPage: Int) = flow {
        emit((getSearchedPhotosFromAPI(query, perPage)))
    }

    private suspend fun getSearchedPhotosFromAPI(query: String, perPage: Int) =
        api.getSearchedPhotos(query, perPage)
            .run {
                if (isSuccessful && body() != null) {
                    return@run body()?.photos
                } else {
                    Error(

                    )
                }
            }
}