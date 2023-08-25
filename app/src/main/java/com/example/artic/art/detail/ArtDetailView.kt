package com.example.artic.art.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.artic.art.UseCase
import com.example.artic.repository.MockRepository
import com.example.artic.state.ViewState
import com.example.artic.ui.theme.ArticTheme
import com.example.artic.view_components.Centered
import com.example.artic.view_components.NavigationView

@Composable
fun ArtDetailView(
    id: Int, viewModel: ArtDetailViewModel = hiltViewModel(), navigateBack: () -> Unit
) {
    viewModel.loadArtDetail(id)
    NavigationView(title = "Back", onBackClicked = navigateBack) { innerPadding ->
        when (val viewState = viewModel.viewState.collectAsState().value) {
            ViewState.Initial, ViewState.Loading -> {
                Centered(padding = innerPadding) {
                    CircularProgressIndicator()
                }
            }

            is ViewState.Failure -> {
                Centered(padding = innerPadding) {
                    Text(viewState.error, modifier = Modifier.padding(16.dp))
                }
            }

            is ViewState.Success -> {
                val artDetail = viewState.data
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        artDetail.title,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Start,
                    )
                    SubcomposeAsyncImage(modifier = Modifier
                        .background(MaterialTheme.colorScheme.onBackground)
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 100.dp),
                        model = artDetail.imageUrl,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = artDetail.title,
                        loading = { CircularProgressIndicator() })
                    Text(
                        text = artDetail.artistName,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                    Text(
                        text = artDetail.style,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.List, contentDescription = "History"
                        )
                        Text(
                            text = "History",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Text(
                        text = artDetail.history,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtDetailViewPreview() {
    val viewModel = ArtDetailViewModel(UseCase(MockRepository()))
    ArticTheme {
        ArtDetailView(1, viewModel) { }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtDetailViewDarkPreview() {
    val viewModel = ArtDetailViewModel(UseCase(MockRepository()))
    ArticTheme(true) {
        ArtDetailView(1, viewModel) { }
    }
}