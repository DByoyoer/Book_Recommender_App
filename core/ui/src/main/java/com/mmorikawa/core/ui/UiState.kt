package com.mmorikawa.core.ui

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


// Ui state from https://medium.com/@ruby.lich/the-power-of-uistate-in-android-development-simplifying-testing-and-reusing-with-ease-5d12decb28d1
sealed interface UiState<out T> {
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String?) : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Empty(val message: String?) : UiState<Nothing>
}

fun <T> Flow<T>.asUiState(
    errorMessage: String? = null
): Flow<UiState<T>> =
    map<T, UiState<T>> {
        UiState.Success(it)
    }.catch {
        emit(UiState.Error(errorMessage ?: it.message))
    }

fun <T> Flow<List<T>>.asUiState(
    emptyMessage: String? = null,
    errorMessage: String? = null
): Flow<UiState<List<T>>> =
    map {
        if (it.isEmpty()) {
            UiState.Empty(emptyMessage)
        } else {
            UiState.Success(it)
        }
    }.catch {
        emit(UiState.Error(errorMessage ?: it.message))
    }