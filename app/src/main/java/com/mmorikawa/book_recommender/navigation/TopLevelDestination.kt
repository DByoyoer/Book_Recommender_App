package com.mmorikawa.book_recommender.navigation


import androidx.compose.ui.graphics.vector.ImageVector
import com.mmorikawa.core.designsystem.icon.BookRecIcons
import com.mmorikawa.book_recommender.R as BookRecR


enum class TopLevelDestination(
    val icon: ImageVector,
    val iconTextLabelId: Int,
    val screenTitleTextId: Int
) {
    HOME(
        icon = BookRecIcons.Home,
        iconTextLabelId = BookRecR.string.home_icon_label,
        screenTitleTextId = BookRecR.string.app_name
    ),
    HISTORY(
        icon = BookRecIcons.History,
        iconTextLabelId = BookRecR.string.history_icon_label,
        screenTitleTextId = BookRecR.string.history_screen_title
    ),
    RECOMMENDATION(
        icon = BookRecIcons.Browse,
        iconTextLabelId = BookRecR.string.browse_icon_label,
        screenTitleTextId = BookRecR.string.browse_screen_title
    ),
    READING_LIST(
        icon = BookRecIcons.List,
        iconTextLabelId = BookRecR.string.list_icon_label,
        screenTitleTextId = BookRecR.string.list_screen_tile
    ),
    SETTINGS(
        icon = BookRecIcons.Settings,
        iconTextLabelId = BookRecR.string.settings,
        screenTitleTextId = BookRecR.string.settings
    )
}