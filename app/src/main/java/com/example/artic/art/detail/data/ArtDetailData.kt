package com.example.artic.art.detail.data

import com.google.gson.annotations.SerializedName

class ArtDetailData(
    val id: Int,
    val title: String?,
    @SerializedName("artist_display") val artistDisplay: String?,
    @SerializedName("image_id") val imageId: String?,
    @SerializedName("publication_history") val publicationHistory: String?,
    @SerializedName("style_title") val styleTitle: String?
)