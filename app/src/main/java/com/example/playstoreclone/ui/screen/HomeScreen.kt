package com.example.playstoreclone.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.playstoreclone.ui.components.AppCard
import com.example.playstoreclone.ui.components.SearchBar
import com.example.playstoreclone.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onAppClick: (String) -> Unit = {}
) {
    val allApps by viewModel.allApps.collectAsStateWithLifecycle()
    val topRatedApps by viewModel.topRatedApps.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading && allApps.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    SearchBar(
                        searchQuery = searchQuery,
                        onSearchQueryChanged = { newQuery ->
                            searchQuery = newQuery
                            viewModel.searchApps(newQuery)
                        }
                    )
                }

                if (searchQuery.isEmpty()) {
                    // Top Rated Apps Section
                    if (topRatedApps.isNotEmpty()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 12.dp, top = 16.dp, end = 12.dp)
                            ) {
                                Text(
                                    text = "Top Rated",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        items(topRatedApps.take(5)) { app ->
                            AppCard(
                                app = app,
                                onClick = { onAppClick(app.id) }
                            )
                        }
                    }

                    // All Apps Section
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 12.dp, top = 20.dp, end = 12.dp)
                        ) {
                            Text(
                                text = "Popular Apps",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    items(allApps) { app ->
                        AppCard(
                            app = app,
                            onClick = { onAppClick(app.id) }
                        )
                    }
                } else {
                    // Search Results
                    if (searchResults.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No apps found",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        items(searchResults) { app ->
                            AppCard(
                                app = app,
                                onClick = { onAppClick(app.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

