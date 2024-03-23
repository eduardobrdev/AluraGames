package br.com.eduardobrdev.aluragames.main

import br.com.eduardobrdev.aluragames.model.Game
import br.com.eduardobrdev.aluragames.services.ApiService
import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    println("Choose your Game ID:")
    val search = reader.nextLine()

    val apiService = ApiService()
    val myInfoGame = apiService.searchGame(search) ?: return

    val myGame = Game(
        myInfoGame.info.title,
        myInfoGame.info.thumb,
        myInfoGame.info.steamAppID
    )

    println("Would you like to add a personalized description to Game: ${myGame.title}? Y/N")
    val choose = reader.nextLine()
    if(choose.equals("y", true)) {
        println("Enter a personalized description for the Game:")
        val description = reader.nextLine()
        myGame.description = description
    } else {
        myGame.description = "Not informed"
    }

    println(myGame)
    println("Search successfully completed.")
}