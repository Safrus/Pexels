package kz.tinker.pexel.data.repository

import kz.tinker.pexel.data.api.PexelApi

const val API_KEY = "563492ad6f917000010000013305ed5e40cd4ea9964c37034d0762f3"

class PhotoRepository(private val api: PexelApi) {
    fun getPhoto() = api.getPhoto(API_KEY, 2014422)
}