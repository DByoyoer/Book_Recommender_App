package com.mmorikawa.book_recommender.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.mmorikawa.book_recommender.R
import com.mmorikawa.book_recommender.navigation.TopLevelDestination
import com.mmorikawa.core.designsystem.component.BookRecNavBar
import com.mmorikawa.core.designsystem.component.BookRecNavBarItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookRecApp() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "App Name Here") })
        }, bottomBar = {
            BookRecBottomBar(
                destinations = TopLevelDestination.entries,
                onNavigateToDestination =  {/* TODO: Navigation */ },
                currentDestination = null
            )
        }, modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = dimensionResource(id = R.dimen.horizontal_margin))
                ) {
                    Text(text = "New Additions")
                    Text(text = "N/A")
                }
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
            val selected = false
            BookRecNavBarItem(selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = { Icon(imageVector = destination.icon, contentDescription = null) },
                label = { Text(stringResource(id = destination.iconTextLabelId)) },
                modifier = modifier
            )
        }
    }

}