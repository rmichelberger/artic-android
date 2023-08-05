package com.example.artic.art.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artic.art.UseCase
import com.example.artic.art.detail.data.ArtDetailViewData
import com.example.artic.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDetailViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    val viewState: MutableStateFlow<ViewState<ArtDetailViewData, String>> =
        MutableStateFlow(ViewState.Initial)

    fun loadArtDetail(id: Int) {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            viewState.value = try {
                ViewState.Success(useCase.getArtDetail(id = id))
            } catch (exception: Exception) {
                ViewState.Failure(exception.localizedMessage ?: "unknown error")
            }
        }
    }
}
