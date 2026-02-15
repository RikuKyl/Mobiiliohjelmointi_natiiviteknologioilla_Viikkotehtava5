package com.example.viikkotehtava5.data.remote

import com.example.viikkotehtava5.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // Hae sää kaupungin nimellä
    // Esim: GET https://api.openweathermap.org/data/2.5/weather?q=Helsinki&appid=xxx&units=metric&lang=fi
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,           // Kaupungin nimi URL-parametrina
        @Query("appid") apiKey: String,     // API-avain (pakollinen)
        @Query("units") units: String = "metric",  // Yksiköt: metric = Celsius
        @Query("lang") lang: String = "fi"  // Kieli: suomi
    ): WeatherResponse  // Retrofit + Gson muuntaa JSON:n automaattisesti

    // Hae sää koordinaateilla
    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "fi"
    ): WeatherResponse
}