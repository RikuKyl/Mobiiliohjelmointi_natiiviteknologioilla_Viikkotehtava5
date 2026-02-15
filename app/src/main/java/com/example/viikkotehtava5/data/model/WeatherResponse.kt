package com.example.viikkotehtava5.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val weather: List<Weather>,   // Lista säätiloista (yleensä 1 elementti)
    val main: Main,               // Lämpötila, kosteus, paine
    val wind: Wind,               // Tuulitiedot
    val name: String,             // Kaupungin nimi
    val sys: Sys                  // Maan koodi, auringonnousu/-lasku
)

data class Weather(
    val id: Int,                  // Sään ID-koodi
    val main: String,             // Pääkategoria: "Clouds", "Rain"
    val description: String,      // Tarkempi kuvaus: "pilvistä"
    val icon: String              // Kuvakkeen koodi: "04d"
)

data class Main(
    val temp: Double,             // Nykyinen lämpötila (°C kun units=metric)
    val feels_like: Double,       // Tuntuu kuin -lämpötila
    @SerializedName("temp_min")   // JSON: "temp_min" → Kotlin: tempMin
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,            // Ilmanpaine (hPa)
    val humidity: Int             // Kosteus (%)
)

data class Wind(
    val speed: Double,            // Tuulen nopeus (m/s)
    val deg: Int                  // Tuulen suunta (asteet)
)

data class Sys(
    val country: String,          // Maakoodi: "FI"
    val sunrise: Long,            // Auringonnousu (Unix timestamp)
    val sunset: Long              // Auringonlasku (Unix timestamp)
)