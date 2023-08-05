package com.example.artic.art.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.artic.art.UseCase
import com.example.artic.art.list.data.ArtViewData
import com.example.artic.repository.MockRepository
import com.example.artic.state.ViewState
import com.example.artic.ui.theme.ArticTheme
import com.example.artic.view_components.Centered
import com.example.artic.view_components.NavigationView

@Composable
fun ArtListView(
    viewModel: ArtListViewModel = hiltViewModel(), navigateToArtDetail: (id: Int) -> Unit
) {
    NavigationView("ArtIC") { innerPadding ->
        when (val viewState = viewModel.viewState.collectAsState().value) {
            ViewState.Initial, ViewState.Loading -> Centered(innerPadding) { CircularProgressIndicator() }
            is ViewState.Failure -> Centered(innerPadding) {
                Text(viewState.error, modifier = Modifier.padding(16.dp))
            }

            is ViewState.Success -> LazyColumn(
                Modifier.padding(horizontal = 16.dp), contentPadding = innerPadding
            ) {
                items(viewState.data) { art ->
                    ArtRow(art = art, navigateToArtDetail = navigateToArtDetail)
                }
            }
        }
    }
}

@Composable
fun ArtRow(
    modifier: Modifier = Modifier,
    art: ArtViewData,
    navigateToArtDetail: (id: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(bottom = 16.dp)
            .clickable(onClick = { navigateToArtDetail(art.id) }),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SubcomposeAsyncImage(modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
                .background(MaterialTheme.colorScheme.onBackground)
                .border(4.dp, MaterialTheme.colorScheme.onBackground, CircleShape),
                model = art.imageUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = art.title,
                loading = { CircularProgressIndicator() })
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row {
                    Icon(
                        Icons.Default.Person, contentDescription = "Artist"
                    )
                    Text(art.artistName)
                }
                Row {
                    Icon(
                        Icons.Default.DateRange, contentDescription = "Date"
                    )
                    Text(text = art.date)
                }
                Row {
                    Icon(
                        Icons.Default.Place, contentDescription = "Place"
                    )
                    Text(art.place)
                }
            }
        }
        Text(art.title, style = MaterialTheme.typography.titleLarge)
        Text(
            art.medium, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.tertiary
        )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun ArtListViewPreview() {
    val viewModel = ArtListViewModel(UseCase(MockRepository()))
    ArticTheme {
        ArtListView(viewModel) {}
    }
}

@Preview(showBackground = true)
@Composable
fun ArtListViewDarkPreview() {
    val viewModel = ArtListViewModel(UseCase(MockRepository()))
    ArticTheme(true) {
        ArtListView(viewModel) {}
    }
}