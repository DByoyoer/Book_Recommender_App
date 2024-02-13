package com.mmorikawa.book_recommender.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mmorikawa.book_recommender.navigation.TopLevelDestination
import com.mmorikawa.feature.history.navigation.historyRoute
import com.mmorikawa.feature.reading_list.navigation.readingListRoute
import com.mmorikawa.feature.recommendation.navigation.recommendationRoute

@Stable
class BookRecAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            recommendationRoute -> TopLevelDestination.RECOMMENDATIONS
            readingListRoute -> TopLevelDestination.READING_LIST
            historyRoute -> TopLevelDestination.HISTORY
            else -> null
        }
}