package com.mmorikawa.book_recommender.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mmorikawa.book_recommender.feature.book_detail.navigation.bookDetailScreen
import com.mmorikawa.book_recommender.feature.book_detail.navigation.navigateToBookDetail
import com.mmorikawa.book_recommender.ui.BookRecAppState
import com.mmorikawa.feature.history.navigation.historyScreen
import com.mmorikawa.feature.history.navigation.navigateToRatingDetail
import com.mmorikawa.feature.reading_list.navigation.readingListScreen
import com.mmorikawa.feature.recommendation.navigation.recommendationRoute
import com.mmorikawa.feature.recommendation.navigation.recommendationsScreen

@Composable
fun BookRecNavHost(
    appState: BookRecAppState,
    modifier: Modifier = Modifier,
    startDestination: String = recommendationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        historyScreen(onBookClick = navController::navigateToRatingDetail)
        recommendationsScreen(onBookClick = navController::navigateToBookDetail)
        readingListScreen()
        bookDetailScreen(navController::popBackStack)
    }
}