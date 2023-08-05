package com.example.artic.repository

import com.example.artic.art.detail.data.ArtDetailData
import com.example.artic.art.list.data.ArtData

interface Repository {
    @Throws(Exception::class)
    suspend fun getArtList(): List<ArtData>

    @Throws(Exception::class)
    suspend fun getArtDetail(id: Int): ArtDetailData
}