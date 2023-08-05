package com.example.artic.repository.api

import com.example.artic.art.detail.data.ArtDetailData
import com.example.artic.art.list.data.ArtData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/v1/artworks")
    suspend fun getArtList(
        @Query("fields") fields: String = "id,title,image_id,date_display,artist_title,place_of_origin,medium_display,publication_history",
        @Query("limit") limit: Int = 100
    ): Response<ApiResponse<List<ArtData>>>

    @GET("/api/v1/artworks/{id}/")
    suspend fun getArtDetail(
        @Path("id") id: Int,
        @Query("fields") fields: String = "id,title,image_id,date_display,place_of_origin,medium_display,publication_history,artist_display,style_title",
    ): Response<ApiResponse<ArtDetailData>>
}