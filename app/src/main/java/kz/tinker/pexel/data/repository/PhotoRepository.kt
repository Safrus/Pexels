package kz.tinker.pexel.data.repository

import kz.tinker.pexel.data.api.PexelApi


class PhotoRepository(private val api: PexelApi) {
    fun getPhoto() = api.getPhoto(2014422)
}