package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun EmptyResultOfSearch() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(44.dp).fillMaxSize()
    ) {
        Text(
            text = "Sorry! No results found...",
            style = MaterialTheme.typography.h2,
            color = VendorAppTheme.colors.textEmptySearch,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Please try a different search request or browse businesses from the list",
            style = MaterialTheme.typography.h3,
            color = VendorAppTheme.colors.textDark,
            fontWeight = FontWeight.Normal
            )
    }
}