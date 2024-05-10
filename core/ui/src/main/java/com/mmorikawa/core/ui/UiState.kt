package com.mmorikawa.core.ui


// Ui state from https://medium.com/@ruby.lich/the-power-of-uistate-in-android-development-simplifying-testing-and-reusing-with-ease-5d12decb28d1
sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String?) : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Empty(val message: String?) : UiState<Nothing>
}