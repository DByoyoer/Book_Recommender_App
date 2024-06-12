package com.mmorikawa.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mmorikawa.feature.search.SearchRoute

const val searchRoute = "browse_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(onBookClick: (Int) -> Unit) {
    composable(route = searchRoute) {
        SearchRoute(onBookClick)
    }
}