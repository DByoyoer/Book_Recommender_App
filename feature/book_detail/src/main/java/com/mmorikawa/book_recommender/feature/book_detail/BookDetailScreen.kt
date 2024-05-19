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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
internal fun BookDetailRoute(
    onBackClick: () -> Unit,
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val bookDetailUiState by viewModel.bookDetailUiState.collectAsStateWithLifecycle()
    BookDetailScreen(onBackClick, bookDetailUiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BookDetailScreen(
    onBackClick: () -> Unit,
    bookDetailUiState: UiState<DetailedBook>
) {
    UiStateWrapper(uiState = bookDetailUiState) { book ->
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() },
                    ) {
                        Icon(
                            imageVector = BookRecIcons.Back,
                            contentDescription = "Back arrow",
                        )
                    }
                }, title = { Text("Book Details") },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = BookRecIcons.AddCircle,
                                contentDescription = "Book Context Actions"
                            )
                        }
                    })
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.padding(it)) {
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
                                "isbn: ${if (book.isbn != "") book.isbn else "N/A"}",
                                fontSize = 12.sp
                            )
                            Text(
                                "isbn13: ${if (book.isbn13 != "") book.isbn13 else "N/A"}",
                                fontSize = 12.sp
                            )
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                book.title,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                            PlainTextStringList(strings = book.authors, prefix = "Authors: ")
                            PlainTextStringList(strings = book.genres, prefix = "Genres: ")
                            Text("Page Count: ${if (book.pages != -1) book.pages else "N/A"}")
                        }
                    }
                    Row {

                    }
                    Text("Book Description:", fontSize = 16.sp)
                    Text(book.description, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun PlainTextStringList(strings: List<String>, prefix: String = "") {
    val displayString = strings.toString().removeSurrounding(prefix = "[", suffix = "]")
    Text(text = "$prefix$displayString")
}