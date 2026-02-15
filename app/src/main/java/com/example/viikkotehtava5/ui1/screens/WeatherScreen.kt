package com.example.viikkotehtava5.ui1.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtava5.ViewModel.WeatherViewModel
import com.example.viikkotehtava5.ui1.components.WeatherContent
import com.example.viikkotehtava5.ui1.components.ErrorScreen
import com.example.viikkotehtava5.ui1.components.SearchBar
import com.example.viikkotehtava5.util.Result

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel()) {
    // collectAsState() muuntaa StateFlown Compose-tilaksi
    val searchQuery by viewModel.searchQuery.collectAsState()
    val weatherState by viewModel.weatherState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Hakukenttä
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.onSearchQueryChange(it) },
            onSearch = { viewModel.searchWeather() }
        )

        // Säätiedot - when käsittelee kaikki Result-tilat
        when (val state = weatherState) {
            is Result.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Result.Success -> {
                WeatherContent(weather = state.data)
            }
            is Result.Error -> {
                ErrorScreen(
                    message = state.exception.message ?: "Virhe",
                    onRetry = { viewModel.searchWeather() }
                )
            }
        }
    }
}

