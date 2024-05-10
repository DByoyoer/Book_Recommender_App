package com.mmorikawa.feature.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun HistoryRoute(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    HistoryScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen() {

    // TODO: Extract to string resources
    val options = listOf("This Month", "This Year", "All Time")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // TODO: Maybe extract this to DesignSystem
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { /* TODO: Add filtering here */ },
                    selected = false, /* TODO: Be able to select the button} */
                ) {
                    Text(text = label, maxLines = 1)
                }
            }
        }
        LazyVerticalGrid(columns = GridCells.Adaptive(300.dp)) {

        }
    }

}