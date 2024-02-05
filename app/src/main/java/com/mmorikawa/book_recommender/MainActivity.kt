package com.mmorikawa.book_recommender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mmorikawa.book_recommender.ui.BookRecApp
import com.mmorikawa.core.designsystem.theme.BookRecommenderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookRecommenderTheme {
                BookRecApp()
            }
        }
    }
}

