package com.example.artic.art

import com.example.artic.art.detail.data.ArtDetailData
import com.example.artic.art.detail.data.ArtDetailViewData
import com.example.artic.art.list.data.ArtData
import com.example.artic.art.list.data.ArtViewData
import com.example.artic.repository.Repository
import javax.inject.Inject

class UseCase @Inject constructor(private val repository: Repository) {

    @Throws(Exception::class)
    suspend fun getArtList(): List<ArtViewData> =
        repository.getArtList().filter { it.publicationHistory != null }.map { convert(it) }

    @Throws(Exception::class)
    suspend fun getArtDetail(id: Int): ArtDetailViewData = convert(repository.getArtDetail(id))

    private fun convert(artData: ArtData): ArtViewData = ArtViewData(artData.id,
        artData.title ?: "Unknown artist",
        artData.dateDisplay ?: "Unknown date",
        artData.placeOfOrigin ?: "Unknown place",
        artData.mediumDisplay ?: "Unknown medium",
        artData.artistTitle ?: "Unknown artist",
        artData.imageId?.let { "https://www.artic.edu/iiif/2/$it/full/400,/0/default.jpg" })

    private fun convert(artDetailData: ArtDetailData): ArtDetailViewData = ArtDetailViewData(
        artDetailData.id,
        artDetailData.title ?: "Unknown art",
        artDetailData.artistDisplay ?: "Unknown artist",
        artDetailData.publicationHistory ?: "Unknown history",
        artDetailData.styleTitle ?: "Unknown style",
        artDetailData.imageId?.let { "https://www.artic.edu/iiif/2/$it/full/873,/0/default.jpg" })
}