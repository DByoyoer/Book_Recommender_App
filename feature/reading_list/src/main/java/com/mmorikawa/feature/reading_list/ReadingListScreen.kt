package com.mmorikawa.feature.reading_list

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ReadingListRoute(
    viewModel: ReadingListViewModel = hiltViewModel()
) {
    ReadingListScreen()
}

@Composable
fun ReadingListScreen() {
    LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {

    }
}