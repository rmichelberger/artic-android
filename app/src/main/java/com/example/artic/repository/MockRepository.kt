package com.example.artic.repository

import com.example.artic.art.detail.data.ArtDetailData
import com.example.artic.art.list.data.ArtData
import kotlinx.coroutines.delay

class MockRepository(private val hasDelay: Boolean = false) : Repository {
    override suspend fun getArtList(): List<ArtData> {
        if (hasDelay)
            delay(500)
        return listOf(
            ArtData(
                8360,
                "Te burao (The Hibiscus Tree)",
                "1892",
                "France",
                "Oil on cotton canvas",
                "Paul Gauguin",
                "604fe021-6008-f666-bce8-456b2fdc9bd4",
                "History"
            ),
            ArtData(
                11434,
                "Salome with the Head of Saint John the Baptist",
                "c. 1639–42",
                "Italy",
                "Oil on cotton canvas",
                "Guido Reni",
                "1f5b595f-dbbd-3c9d-ca49-9263c3d93841",
                "History"
            ),
            ArtData(
                11723,
                "Woman at Her Toilette",
                "1875/80",
                "France",
                "Oil on cotton canvas",
                "Berthe Morisot",
                "78c80988-6524-cec7-c661-a4c0a706d06f",
                "History"
            )
        )
    }

    override suspend fun getArtDetail(id: Int): ArtDetailData {
        if (hasDelay)
            delay(500)
        return ArtDetailData(
            id = 65479,
            title = "Cottages with a Woman Working in the Middle Ground",
            dateDisplay = "1890",
            placeOfOrigin = "Netherlands",
            mediumDisplay = "Charcoal, reed pen and black ink, blue pastel, and white chalk on blue-gray laid paper",
            artistDisplay = "Vincent van Gogh\nDutch, 1853-1890",
            imageId = "c60f377f-37e3-a5e5-6a49-2099fb475355",
            publicationHistory = "J. B. De la Faille, L'Oeuvre de Vincent Van Gogh (Brussels and Paris, 1928), p. 178, no. 1642, pl. CCIX.\n\nJ. B. de la Faille, The Works of Vincent van Gogh: His Paintings and Drawings (Amsterdam, 1970), p. 560, no. 1642 (ill.).\n\nJan Hulsker, The Complete Van Gogh: Paintings, Drawings, Sketches (New York, 1980), no. 1994 (ill.).\n\nJ. E. Hijbroek and E.L. Wouthuysen, Kunst, Kennis en Commercis: De Kunsthandelaar J.H. de Bois, 1878-1946 (Amsterdam, 1993), p. 206.\n\nBelinda Thomson, Van Gogh (Chicago, 2001), pp. 81,  102, and 107, pl. 15.",
            styleTitle = null
        )
    }
}