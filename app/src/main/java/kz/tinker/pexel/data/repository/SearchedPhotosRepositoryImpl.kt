package kz.tinker.pexel.data.repository

import kotlinx.coroutines.flow.flow
import kz.tinker.pexel.data.api.PexelApi

class SearchedPhotosRepositoryImpl(private val api: PexelApi) : SearchedPhotosRepository {

    override fun getSearchedPhotos(query: String, perPage: Int) = flow {
        emit((getSearchedPhotosFromAPI(query, perPage)))
    }

    override suspend fun getSearchedPhotosFromAPI(query: String, perPage: Int) =
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