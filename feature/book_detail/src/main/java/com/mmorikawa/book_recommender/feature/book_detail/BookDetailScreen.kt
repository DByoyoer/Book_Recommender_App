package com.mmorikawa.book_recommender.feature.book_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.core.model.DetailedBook
import com.mmorikawa.core.ui.UiState
import com.mmorikawa.core.ui.UiStateWrapper
import kotlinx.coroutines.launch

@Composable
internal fun BookDetailRoute(
    onBackClick: () -> Unit, viewModel: BookDetailViewModel = hiltViewModel()
) {
    val bookDetailUiState by viewModel.bookDetailUiState.collectAsStateWithLifecycle()
    BookDetailScreen(
        state = bookDetailUiState,
        onAddToReadingList = { viewModel.addBookToReadingList() },
        onUndo = { viewModel.removeBookFromReadingList() },
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BookDetailScreen(
    state: UiState<DetailedBook>,
    onAddToReadingList: () -> Unit,
    onBackClick: () -> Unit,
    onUndo: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    UiStateWrapper(uiState = state) { book ->
        Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
            CenterAlignedTopAppBar(navigationIcon = {
                IconButton(
                    onClick = { onBackClick() },
                ) {
                    Icon(
                        imageVector = BookRecIcons.Back,
                        contentDescription = "Back arrow",
                    )
                }
            }, title = { Text("Book Details") }, actions = {
                BookDetailDropDownButton(readingListOnclick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Book added to reading list", actionLabel = "Undo"
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                onUndo()
                            }

                            SnackbarResult.Dismissed -> {}
                        }
                    }
                    onAddToReadingList()
                }, ratingOnClick = { /*TODO*/ })
            })
        },

            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.padding(it)) {
                BookDetailContentBody(book = book)
            }
        }
    }
}

@Composable
fun PlainTextStringList(strings: List<String>, prefix: String = "") {
    val displayString = strings.toString().removeSurrounding(prefix = "[", suffix = "]")
    Text(text = "$prefix$displayString")
}

@Composable
fun BookDetailDropDownButton(readingListOnclick: () -> Unit, ratingOnClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = BookRecIcons.AddCircle, contentDescription = "Book Context Actions"
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("Add to reading list") },
                onClick = { readingListOnclick() })
            HorizontalDivider()
            DropdownMenuItem(text = { Text("Add to ratings") }, onClick = { ratingOnClick() })
        }
    }
}

@Composable
fun BookDetailContentBody(book: DetailedBook) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Column {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = "${book.title} cover image.",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(250.dp)
                        .width(150.dp)
                        .padding(16.dp)
                )
                Text(
                    "isbn: ${if (book.isbn != "") book.isbn else "N/A"}", fontSize = 12.sp
                )
                Text(
                    "isbn13: ${if (book.isbn13 != "") book.isbn13 else "N/A"}", fontSize = 12.sp
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    book.title, fontSize = 24.sp, modifier = Modifier.padding(vertical = 16.dp)
                )
                PlainTextStringList(strings = book.authors, prefix = "Authors: ")
                PlainTextStringList(strings = book.genres, prefix = "Genres: ")
                Text("Page Count: ${if (book.pages != -1) book.pages else "N/A"}")
            }
        }
        Text("Book Description:", fontSize = 16.sp)
        Text(book.description, fontSize = 12.sp)
    }
}