package com.mmorikawa.feature.reading_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mmorikawa.feature.reading_list.ReadingListRoute

const val readingListRoute = "reading_list_route"

fun NavController.navigateToReadingList(navOptions: NavOptions? = null) {
    this.navigate(readingListRoute, navOptions)
}

fun NavGraphBuilder.readingListScreen(onBookClick: (Int) -> Unit) {
    composable(route = readingListRoute) {
        ReadingListRoute(onBookClick = onBookClick)
    }
}