package kz.tinker.pexel.data.model


import com.google.gson.annotations.SerializedName

data class CuratedPhotos(
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Photo>
)