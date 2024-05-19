package com.mmorikawa.book_recommender.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mmorikawa.book_recommender.navigation.BookRecNavHost
import com.mmorikawa.book_recommender.navigation.TopLevelDestination
import com.mmorikawa.core.designsystem.component.BookRecNavBar
import com.mmorikawa.core.designsystem.component.BookRecNavBarItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRecApp(appState: BookRecAppState = rememberBookRecAppState()) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        val currentTopLevelDestination = appState.currentTopLevelDestination
        // TODO: Use state to determine
        Scaffold(contentWindowInsets = WindowInsets(0, 0, 0, 0), topBar = {
            // TODO: Extract to designsystem and need to elaborate for extra features
            if (currentTopLevelDestination != null) {
                CenterAlignedTopAppBar(title = {
                    Text(stringResource(id = currentTopLevelDestination.screenTitleTextId))
                })
            }
        }, bottomBar = {
            if (currentTopLevelDestination != null) {
                BookRecBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }, modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                BookRecNavHost(appState = appState)
            }
        }


    }
}


@Composable
private fun BookRecBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    BookRecNavBar(
        modifier = Modifier
    ) {
        destinations.forEach { destination ->
            // TODO: Determine if current destination is selected
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            BookRecNavBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = { Icon(imageVector = destination.icon, contentDescription = null) },
                label = { Text(stringResource(id = destination.iconTextLabelId)) },
                modifier = modifier
            )
        }
    }

}

// Taken from NowInAndroid app without understanding how it works yet
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

