package com.example.artic.art.list.data

import com.google.gson.annotations.SerializedName

data class ArtData(
    val id: Int,
    val title: String?,
    @SerializedName("date_display") val dateDisplay: String?,
    @SerializedName("place_of_origin") val placeOfOrigin: String?,
    @SerializedName("medium_display") val mediumDisplay: String?,
    @SerializedName("artist_title") val artistTitle: String?,
    @SerializedName("image_id") val imageId: String?,
    @SerializedName("publication_history") val publicationHistory: String?
)