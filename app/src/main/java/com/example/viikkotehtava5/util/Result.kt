package com.example.viikkotehtava5.util

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()          // Onnistunut vastaus
    data class Error(val exception: Exception) : Result<Nothing>()  // Virhe
    object Loading : Result<Nothing>()                         // Ladataan...
}