package com.mmorikawa.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.SimpleBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper


@Composable
fun SearchRoute(onBookClick: (Int) -> Unit, viewModel: SearchViewModel = hiltViewModel()) {
    val uiState by viewModel.searchResult.collectAsStateWithLifecycle()
    SearchScreen(
        state = uiState,
        onBookClick = onBookClick,
        onSearch = viewModel::onSearch,
        onUpdateQuery = viewModel::onUpdateQuery,
        searchQuery = viewModel.queryText
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: UiState<List<SimpleBook>>,
    onBookClick: (Int) -> Unit,
    onSearch: (String) -> Unit,
    onUpdateQuery: (String) -> Unit,
    searchQuery: String
) {
    val isActive = remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SearchBar(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            query = searchQuery,
            onQueryChange = onUpdateQuery,
            onSearch = {
                isActive.value = false
                onSearch(it)
            },
            active = isActive.value,
            onActiveChange = { isActive.value = it },
            leadingIcon = { Icon(BookRecIcons.Search, contentDescription = "Search") },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(BookRecIcons.Filter, contentDescription = "Filter")
                }
            }) {
            /* TODO: Display recent searches */
            Text(
                "Recent Searches:",
                color = Color.Gray,
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp
            )
            LazyColumn() {
                items(listOf("")) {
                    Box(modifier = Modifier.clickable { }) {
                        Text(text = it, modifier = Modifier.padding(8.dp))
                    }
                }
            }

        }
        UiStateWrapper(uiState = state) { feed ->
            LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {
                items(items = feed, key = { item -> item.id }) { simpleBook ->
                    ListItem(modifier = Modifier.clickable { onBookClick(simpleBook.id) },
                        leadingContent = {
                            AsyncImage(
                                model = simpleBook.coverUrl,
                                contentDescription = "${simpleBook.title} book cover image"
                            )
                        },
                        headlineContent = { Text(simpleBook.title) },
                        supportingContent = { Text(simpleBook.genres.toString()) },
                        overlineContent = { Text(simpleBook.authors.toString()) })
                }
            }
        }
    }
}