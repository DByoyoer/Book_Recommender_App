package com.mmorikawa.feature.recommendation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mmorikawa.feature.recommendation.RecommendationRoute

const val recommendationRoute = "recommendation_route"

fun NavController.navigateToRecommendations(navOptions: NavOptions? = null) {
    this.navigate(recommendationRoute, navOptions)
}

fun NavGraphBuilder.recommendationsScreen() {
    composable(route = recommendationRoute) {
        RecommendationRoute()
    }
}