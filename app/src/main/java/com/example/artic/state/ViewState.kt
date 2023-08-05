package com.example.artic.state

sealed class ViewState<out T, out E> {
    object Initial : ViewState<Nothing, Nothing>()
    object Loading : ViewState<Nothing, Nothing>()
    data class Failure<E>(val error: E) : ViewState<Nothing, E>()
    data class Success<T>(val data: T) : ViewState<T, Nothing>()
}