package com.example.viikkotehtava5.repository


import com.example.viikkotehtava5.data.model.WeatherResponse
import com.example.viikkotehtava5.data.remote.RetrofitClient
import com.example.viikkotehtava5.BuildConfig
import retrofit2.HttpException
import java.io.IOException
import com.example.viikkotehtava5.util.Result


class WeatherRepository {

    // Käytetään RetrofitClientin singleton-instanssia
    private val apiService = RetrofitClient.weatherApiService

    // API-avain - hanki ilmaiseksi: https://openweathermap.org/api
    val apiKey = BuildConfig.OPENWEATHER_API_KEY

    // suspend = tämä funktio voi "pysähtyä" odottamaan vastausta
    // ilman säikeen blokkausta (Kotlin Coroutines)
    suspend fun getWeather(city: String): Result<WeatherResponse> {
        return try {
            // Kutsutaan API:a - Retrofit hoitaa HTTP-pyynnön
            val response = apiService.getWeather(city, BuildConfig.OPENWEATHER_API_KEY)
            Result.Success(response)     // Palautetaan onnistunut tulos
        } catch (e: IOException) {
            // Verkkovirhe (ei nettiyhteyttä, timeout)
            Result.Error(Exception("Verkkovirhe: ${e.message}"))
        } catch (e: HttpException) {
            // HTTP-virhe (404 Not Found, 500 Server Error)
            Result.Error(Exception("Palvelinvirhe: ${e.code()}"))
        } catch (e: Exception) {
            // Muu virhe
            Result.Error(Exception("Tuntematon virhe: ${e.message}"))
        }
    }
}