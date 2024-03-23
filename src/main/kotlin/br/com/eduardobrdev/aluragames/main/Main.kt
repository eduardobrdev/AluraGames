package br.com.eduardobrdev.aluragames.main

import br.com.eduardobrdev.aluragames.model.Game
import br.com.eduardobrdev.aluragames.model.Gamer
import br.com.eduardobrdev.aluragames.services.ApiService
import java.util.*

fun main() {
    val reader = Scanner(System.`in`)
    val gamer = Gamer.registerGamer(reader)
    println("Registration Successful!")
    println(gamer)

    do {
        var response: String?
        println("Hi ${gamer.name}, Choose your Game ID:")
        val search = reader.nextLine()

        val apiService = ApiService()
        val myInfoGame = apiService.searchGame(search)
        if (myInfoGame == null) {
            println("Looking for another game? Y/N")
            response = reader.nextLine()
            continue
        }

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

        gamer.gamesSearch.add(myGame)

        println(myGame)
        println("Looking for another game? Y/N")
        response = reader.nextLine()

    } while (response.equals("y", true))

    println("\n Games searched sorted by Title:")
    gamer.gamesSearch.sortBy { it?.title }

    gamer.gamesSearch.forEach {
        println("Title: " + it?.title)
    }

    println("Search successfully completed.")
}