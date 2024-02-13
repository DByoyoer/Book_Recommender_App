package com.mmorikawa.book_recommender.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mmorikawa.book_recommender.navigation.TopLevelDestination
import com.mmorikawa.feature.history.navigation.historyRoute
import com.mmorikawa.feature.history.navigation.navigateToHistory
import com.mmorikawa.feature.reading_list.navigation.navigateToReadingList
import com.mmorikawa.feature.reading_list.navigation.readingListRoute
import com.mmorikawa.feature.recommendation.navigation.navigateToRecommendations
import com.mmorikawa.feature.recommendation.navigation.recommendationRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberBookRecAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): BookRecAppState {
    return remember(
        navController,
        coroutineScope
    ) {
        BookRecAppState(navController)
    }
}

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

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true

            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.RECOMMENDATIONS -> navController.navigateToRecommendations(
                topLevelNavOptions
            )

            TopLevelDestination.READING_LIST -> navController.navigateToReadingList(
                topLevelNavOptions
            )

            TopLevelDestination.HISTORY -> navController.navigateToHistory(topLevelNavOptions)

            TopLevelDestination.SETTINGS -> null

            TopLevelDestination.HOME -> null


        }
    }
}