package com.example.artic.repository

import android.accounts.NetworkErrorException
import com.example.artic.art.detail.data.ArtDetailData
import com.example.artic.art.list.data.ArtData
import com.example.artic.repository.api.ApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    @Throws(NetworkErrorException::class)
    override suspend fun getArtList(): List<ArtData> =
        apiService.getArtList().body()?.data?.filter { it.publicationHistory != null }
            ?: throw NetworkErrorException("no data")

    @Throws(NetworkErrorException::class)
    override suspend fun getArtDetail(id: Int): ArtDetailData =
        apiService.getArtDetail(id).body()?.data ?: throw NetworkErrorException("no data")
}