package kz.tinker.pexel.data.repository

import kotlinx.coroutines.flow.flow
import kz.tinker.pexel.data.api.PexelApi

class CuratedPhotosRepository(private val api: PexelApi) {

    fun getCuratedPhotos(perPage: Int, page: Int) = flow {
        emit((getCuratedPhotosFromAPI(perPage, page)))
    }

    private suspend fun getCuratedPhotosFromAPI(perPage: Int, page: Int) =
        api.getCuratedPhotos(perPage, page)
            .run {
                if (isSuccessful && body() != null) {
                    return@run body()?.photos
                } else {
                    Error(

                    )
                }
            }

}