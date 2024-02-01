package com.mmorikawa.book_recommender.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import com.mmorikawa.book_recommender.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "App Name Here") })
    }, bottomBar = {
        NavigationBar {
            // Home
            NavigationBarItem(selected = true, // This will be stored in some kind of UiState
                onClick = { /*TODO*/ }, icon = {
                    Icon(imageVector = Icons.Outlined.Home, "Home")
                }, label = { Text("Home") }, alwaysShowLabel = true
            )
            // Reading History
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.history),
                    "History"
                )
            }, label = { Text("History") }, alwaysShowLabel = true
            )
            // Browse/Search Recs
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.browse),
                    "Browse"
                )
            }, label = { Text("Browse") }, alwaysShowLabel = true
            )
            // Reading List
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.List,
                    "Read List"
                )
            }, label = { Text("Read List") }, alwaysShowLabel = true
            )
            // Settings
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    "Settings"
                )
            }, label = { Text("Settings") }, alwaysShowLabel = true
            )

        }
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