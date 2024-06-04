package com.mmorikawa.feature.history.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mmorikawa.feature.history.RatingDetailRoute

private const val ratingDetailIdArg = "bookId"

internal class RatingDetailArgs(val bookId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[ratingDetailIdArg]) as Int)
}

fun NavController.navigateToRatingDetail(bookId: Int) {
    this.navigate("rating_detail/$bookId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.ratingDetailScreen(onBackClick: () -> Unit) {
    composable(
        route = "rating_detail/{$ratingDetailIdArg}",
        arguments = listOf(navArgument(ratingDetailIdArg) { type = NavType.IntType })
    ) {
        RatingDetailRoute(onBackClick)
    }
}