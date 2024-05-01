package com.mmorikawa.feature.recommendation

import androidx.lifecycle.ViewModel
import com.mmorikawa.core.testing.data.getFakeUserBookData
import com.mmorikawa.core.ui.BookFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
) : ViewModel() {
    // TODO: Set up repository
    val feedUiState = BookFeedUiState.Success(getFakeUserBookData(25))
}