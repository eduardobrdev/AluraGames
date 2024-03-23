package br.com.eduardobrdev.aluragames.services

import br.com.eduardobrdev.aluragames.model.GameInfo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiService {
    fun searchGame(id: String): GameInfo? {
        val url = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())

        val json = response.body()
        val gson = Gson()

        var myInfoGame:GameInfo? = null
        val result = runCatching {
            myInfoGame = gson.fromJson(json, GameInfo::class.java)
        }

        result.onSuccess {
            return myInfoGame
        }

        result.onFailure {
            println("Game not Found.")
        }

        return null
    }
}