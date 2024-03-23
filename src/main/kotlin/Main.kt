package org.example

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner

fun main() {
    val reader = Scanner(System.`in`)
    println("Choose your Game ID:")
    val search = reader.nextLine()

    val url = "https://www.cheapshark.com/api/1.0/games?id=$search"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build()

    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()

    val gson = Gson()

    var myGame:Game? = null

    val result = runCatching {
        val myInfoGame = gson.fromJson(json, GameInfo::class.java)
        myGame = Game(
            myInfoGame.info.title,
            myInfoGame.info.thumb,
            myInfoGame.info.steamAppID
        )
    }

    result.onFailure {
        println("Game not Found.")
    }

    result.onSuccess {
        println("Would you like to add a personalized description to Game: ${myGame?.title}? Y/N")
        val choose = reader.nextLine()
        if(choose.equals("y", true)) {
            println("Enter a personalized description for the Game:")
            val description = reader.nextLine()
            myGame?.description = description
        } else {
            myGame?.description = "Not informed"
        }

        println(myGame)
    }

    result.onSuccess {
        println("Search successfully completed.")
    }
}