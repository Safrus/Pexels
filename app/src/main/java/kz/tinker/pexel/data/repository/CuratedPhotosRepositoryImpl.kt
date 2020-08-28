package kz.tinker.pexel.data.repository

import kotlinx.coroutines.flow.flow
import kz.tinker.pexel.data.api.PexelApi

class CuratedPhotosRepositoryImpl(private val api: PexelApi) : CuratedPhotosRepository {

    override fun getCuratedPhotos(perPage: Int, page: Int) = flow {
        emit((getCuratedPhotosFromAPI(perPage, page)))
    }

    override suspend fun getCuratedPhotosFromAPI(perPage: Int, page: Int) =
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