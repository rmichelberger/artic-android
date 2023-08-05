package com.example.artic.art.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artic.art.UseCase
import com.example.artic.art.list.data.ArtViewData
import com.example.artic.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtListViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    val viewState: MutableStateFlow<ViewState<List<ArtViewData>, String>> =
        MutableStateFlow(ViewState.Initial)

    init {
        loadArtList()
    }

    private fun loadArtList() {
        viewState.value = ViewState.Loading
        viewModelScope.launch {
            viewState.value = try {
                ViewState.Success(useCase.getArtList())
            } catch (exception: Exception) {
                ViewState.Failure(exception.localizedMessage ?: "unknown error")
            }
        }
    }
}