package com.mmorikawa.book_recommender.feature.book_detail.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mmorikawa.book_recommender.feature.book_detail.BookDetailRoute

private const val bookDetailIdArg = "bookId"

internal class BookDetailArgs(val bookId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(checkNotNull(savedStateHandle[bookDetailIdArg]) as Int)
}

fun NavController.navigateToBookDetail(bookId: Int) {
    this.navigate("book_detail/$bookId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.bookDetailScreen(onBackClick: () -> Unit) {
    composable(
        route = "book_detail/{$bookDetailIdArg}",
        arguments = listOf(navArgument(bookDetailIdArg) { type = NavType.IntType })
    ) {
        BookDetailRoute(onBackClick)
    }
}